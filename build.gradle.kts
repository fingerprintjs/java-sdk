import java.net.URL
import java.nio.file.Files
import java.nio.file.StandardCopyOption

val projectVersion: String by project

allprojects {
    group = "com.github.fingerprintjs"
    version = projectVersion
}

plugins {
    alias(libs.plugins.google.osdetector)
}

// Register formatting tasks for each subproject

val googleJavaFormatExeFile = layout.buildDirectory.file("google-java-format.exe")
val googleJavaFormatOsSuffixMap = mapOf(
    "osx" to "darwin",
    "linux" to "linux",
    "windows" to "windows",
)
val googleJavaFormatArchSuffixMap = mapOf(
    "x86_64" to "x86-64",
    "aarch_64" to "arm64"
)
val googleJavaFormatBinarySuffixMap = mapOf(
    "osx" to "",
    "linux" to "",
    "windows" to ".exe"
)

tasks.register("downloadGoogleJavaFormat") {
    val osSuffix = googleJavaFormatOsSuffixMap[osdetector.os]
    val archSuffix = googleJavaFormatArchSuffixMap[osdetector.arch]
    val binarySuffix = googleJavaFormatBinarySuffixMap[osdetector.os]
    val downloadUrl = "https://github.com/google/google-java-format/releases/download/v1.34.1/google-java-format_$osSuffix-$archSuffix$binarySuffix"
    outputs.file(googleJavaFormatExeFile)

    doLast {
        val file = googleJavaFormatExeFile.get().asFile
        if (!file.exists()) {
            println("Downloading google-java-format for $osSuffix-$archSuffix")
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

    tasks.register<Exec>("format") {
        dependsOn(rootProject.tasks.named("downloadGoogleJavaFormat"))

        executable = googleJavaFormatExeFile.get().asFile.toPath().toString()
        args = listOf("--replace") + inputFiles
    }

    tasks.register<Exec>("formatCheck") {
        dependsOn(rootProject.tasks.named("downloadGoogleJavaFormat"))

        doFirst {
            println("The following source files need to be formatted:")
        }

        executable = googleJavaFormatExeFile.get().asFile.toPath().toString()
        args = listOf("--dry-run", "--set-exit-if-changed") + inputFiles
    }
}

subprojects {
    registerFormatTasks()
}
