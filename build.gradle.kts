import java.net.URL
import java.nio.file.Files
import java.nio.file.StandardCopyOption

val projectVersion: String by project

allprojects {
    group = "com.github.fingerprintjs"
    version = projectVersion
}

// Register formatting tasks for each subproject

val googleJavaFormatJarFile = layout.buildDirectory.file("google-java-format-all-deps.jar")

tasks.register("downloadGoogleJavaFormat") {
    val downloadUrl = "https://github.com/google/google-java-format/releases/download/v1.34.1/google-java-format-1.34.1-all-deps.jar"
    outputs.file(googleJavaFormatJarFile)

    doLast {
        val file = googleJavaFormatJarFile.get().asFile
        if (!file.exists()) {
            println("Downloading google-java-format")
            URL(downloadUrl).openStream().use { input ->
                Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING)
            }
            file.setExecutable(true)
        } else {
            println("google-java-format already downloaded")
        }
    }
}


fun Project.registerFormatTasks() {
    val inputFiles = fileTree("src") {
        include("**/*.java")
    }.files.map { it.absolutePath }


    tasks.register<JavaExec>("format") {
        dependsOn(rootProject.tasks.named("downloadGoogleJavaFormat"))

        onlyIf {
            // google-java-format requires Java 17 or later.
            // Skip if running with Java 11
            JavaVersion.current().isCompatibleWith(JavaVersion.VERSION_17)
        }

        classpath = files(googleJavaFormatJarFile)
        args = listOf("--replace") + inputFiles
    }

    tasks.register<JavaExec>("formatCheck") {
        dependsOn(rootProject.tasks.named("downloadGoogleJavaFormat"))

        doFirst {
            println("The following source files need to be formatted:")
        }

        onlyIf {
            // google-java-format requires Java 17 or later.
            // Skip if running with Java 11
            JavaVersion.current().isCompatibleWith(JavaVersion.VERSION_17)
        }

        classpath = files(googleJavaFormatJarFile)
        args = listOf("--dry-run", "--set-exit-if-changed") + inputFiles
    }
}

subprojects {
    registerFormatTasks()
}
