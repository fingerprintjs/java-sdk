import java.net.URL
import java.nio.file.Files
import java.nio.file.StandardCopyOption
import java.security.MessageDigest

val projectVersion: String by project

allprojects {
    group = "com.github.fingerprintjs"
    version = projectVersion
}

val googleJavaFormatExeFile = layout.buildDirectory.file("google-java-format.exe")

// SHA-256 of each google-java-format native binary, keyed by the platform part of the release
// asset name. These belong to the version in gradle/libs.versions.toml
// (`versions.google-java-format`)
//
// NOTE: When bumping it, take the new digests from the GitHub release page and replace all of
// them.
val googleJavaFormatSha256 = mapOf(
    "darwin-arm64" to "572c8be5ea4e15c674f7f9c89f9c0a4b9336950d9c42c07220557e97a4002bce",
    "linux-arm64" to "37d27263fce029fc59aa4e1b556ac6997d0eb2266a38ba2b8746b709f6c77b11",
    "linux-x86-64" to "8dc71663a6c9cb17b02ba9709bfab5c4de59de6f4ac133fd7a1d5a4394b1193b",
    "windows-x86-64.exe" to "23a6acedcfd9253da8413984dd1e3aecab32f00543477566d1abe5ae5aab1019",
)

fun googleJavaFormatPlatform(): String {
    val osName = System.getProperty("os.name").lowercase()
    val os = when {
        osName.startsWith("mac") -> "darwin"
        osName.startsWith("linux") -> "linux"
        osName.startsWith("windows") -> "windows"
        else -> throw GradleException("google-java-format: unsupported OS '$osName'")
    }
    val arch = when (val osArch = System.getProperty("os.arch").lowercase()) {
        "aarch64", "arm64" -> "arm64"
        "x86_64", "amd64" -> "x86-64"
        else -> throw GradleException("google-java-format: unsupported architecture '$osArch'")
    }
    return if (os == "windows") "$os-$arch.exe" else "$os-$arch"
}

fun sha256Hex(file: File): String {
    val digest = MessageDigest.getInstance("SHA-256")
    file.inputStream().use { input ->
        val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
        while (true) {
            val read = input.read(buffer)
            if (read < 0) break
            digest.update(buffer, 0, read)
        }
    }
    return digest.digest().joinToString("") { "%02x".format(it) }
}

tasks.register("downloadGoogleJavaFormat") {
    val googleJavaFormatVersion = libs.versions.google.java.format.get()
    outputs.file(googleJavaFormatExeFile)

    onlyIf {
        System.getenv("JITPACK")?.lowercase() != "true"
    }

    doLast {
        val platform = googleJavaFormatPlatform()
        val expectedSha256 = googleJavaFormatSha256[platform]
            ?: throw GradleException("google-java-format: no pinned SHA-256 for '$platform'")
        val file = googleJavaFormatExeFile.get().asFile

        if (file.exists() && sha256Hex(file) == expectedSha256) {
            println("google-java-format already downloaded")
            return@doLast
        }

        println("Downloading google-java-format $googleJavaFormatVersion for $platform")
        val downloadUrl = "https://github.com/google/google-java-format/releases/download/v$googleJavaFormatVersion/google-java-format_$platform"
        val tempFile = File(file.parentFile, "${file.name}.download")
        file.parentFile.mkdirs()
        URL(downloadUrl).openStream().use { input ->
            Files.copy(input, tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING)
        }

        val actualSha256 = sha256Hex(tempFile)
        if (actualSha256 != expectedSha256) {
            tempFile.delete()
            throw GradleException(
                "google-java-format: SHA-256 mismatch for $downloadUrl (expected $expectedSha256, got $actualSha256)"
            )
        }

        Files.move(tempFile.toPath(), file.toPath(), StandardCopyOption.REPLACE_EXISTING)
        file.setExecutable(true)
    }
}


fun Project.registerFormatTasks() {
    // Resolve lazily: files may be deleted/regenerated between configuration and execution
    // (e.g., openApiGenerate removes and rewrites sources before format runs).
    fun srcJavaFiles(): List<String> = fileTree("src") {
        include("**/*.java")
    }.files.map { it.absolutePath }

    tasks.register("format") {
        dependsOn(rootProject.tasks.named("downloadGoogleJavaFormat"))

        onlyIf {
            System.getenv("JITPACK")?.lowercase() != "true"
        }

        doLast {
            val inputFiles = srcJavaFiles()

            // First, fix the imports
            exec {
                executable = googleJavaFormatExeFile.get().asFile.toPath().toString()
                args = listOf("--replace", "--fix-imports-only") + inputFiles
            }

            // Second, format the code. This ensures that the removal of unused imports does not
            // introduce the need to run another formatting pass to pass the formatting checks.
            exec {
                executable = googleJavaFormatExeFile.get().asFile.toPath().toString()
                args = listOf("--replace", "--skip-javadoc-formatting", "--skip-reflowing-long-strings") + inputFiles
            }
        }
    }

    tasks.register("checkFormat") {
        dependsOn(rootProject.tasks.named("downloadGoogleJavaFormat"))

        doLast {
            println("The following source files need to be formatted:")
            exec {
                executable = googleJavaFormatExeFile.get().asFile.toPath().toString()
                args = listOf("--dry-run", "--set-exit-if-changed", "--skip-javadoc-formatting", "--skip-reflowing-long-strings") + srcJavaFiles()
            }
        }
    }
}

subprojects {
    registerFormatTasks()

    plugins.withType<org.gradle.api.plugins.JavaPlugin> {
        tasks.withType<JavaCompile> {
            // Target java 11
            options.release = 11

            // Enable all warnings and fail the build for any warnings
            options.compilerArgs.addAll(listOf("-Xlint:all", "-Werror"))
        }

        // Print Java toolchain for compilation
        tasks.withType<JavaCompile>().configureEach {
            doFirst {
                println("Compiling with Java: ${javaCompiler.get().metadata.installationPath} (version: ${javaCompiler.get().metadata.languageVersion})")
            }
        }

        // Print Java toolchain used for running tests
        tasks.withType<Test>().configureEach {
            doFirst {
                val launcher = javaLauncher.orNull
                if (launcher != null) {
                    println("Testing with Java: ${launcher.metadata.installationPath} (version: ${launcher.metadata.languageVersion})")
                } else {
                    println("Testing with Java: launcher not set")
                }
            }
        }

        // Print Java toolchain used for running
        tasks.withType<JavaExec>().configureEach {
            doFirst {
                val launcher = javaLauncher.orNull
                if (launcher != null) {
                    println("Running JavaExec with Java: ${launcher.metadata.installationPath} (version: ${launcher.metadata.languageVersion})")
                } else {
                    println("Running JavaExec with Java: launcher not set")
                }
            }
        }
    }
}
