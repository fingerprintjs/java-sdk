
val projectVersion: String by project

group = "com.github.fingerprintjs"
version = projectVersion

java {
    // Target the earliest support Java version
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.withType<JavaCompile> {
    options.compilerArgs.addAll(listOf("-Xlint:all", "-Werror"))
}

plugins {
    alias(libs.plugins.openapi.generator)
    `java-library`
    `maven-publish`
}

repositories {
    mavenLocal()
    mavenCentral()
}

publishing {
    publications {
        register("localMavenJava", MavenPublication::class) {
            groupId = "com.github.fingerprintjs"
            artifactId = "java-sdk"
            version = projectVersion
            from(components["java"])
        }
    }
}

dependencies {
    implementation(libs.swagger.annotations)
    api(libs.jersey.client)
    api(libs.jersey.hk2)
    api(libs.jersey.media.json)
    api(libs.jersey.media.multipart)
    api(libs.jersey.apache.connector)
    api(libs.jackson.core)
    api(libs.jackson.annotations)
    api(libs.jackson.databind)
    api(libs.jackson.jsr310)
    api(libs.jakarta.annotation.api)
    testImplementation(libs.junit.jupiter.api)
    testImplementation(libs.junit.platform.launcher)
    testRuntimeOnly(libs.junit.jupiter.engine)
    testImplementation(libs.mockito)
}

sourceSets {
    main {
        java {
            srcDir("src/main/java")
        }
    }
}

openApiGenerate {
    generatorName.set("java")
    inputSpec.set("$rootDir/res/fingerprint-server-api.yaml")
    outputDir.set(layout.buildDirectory.dir("generated").get().asFile.path)
    groupId.set("com.github.fingerprintjs")
    id.set("java-sdk")
    version.set(projectVersion)
    apiPackage.set("com.fingerprint.v4.api")
    modelPackage.set("com.fingerprint.v4.model")
    invokerPackage.set("com.fingerprint.v4.sdk")
    library.set("jersey3")
    templateDir.set("$rootDir/template")

    gitHost.set("github.com")
    gitRepoId.set("java-sdk")
    gitUserId.set("fingerprintjs")
    configOptions.put("hideGenerationTimestamp", "true")
    configOptions.put("openApiNullable", "false")
    configOptions.put("disallowAdditionalPropertiesIfNotPresent", "false")
    configOptions.put("useOneOfInterfaces", "true")
}

tasks.register("removeDocs") {
    doLast {
        fileTree("$rootDir/docs").files
            .filter { it.isFile && it.name != "DecryptionKey.md" && it.name != "Sealed.md" }
            .forEach {
                it.delete()
            }
    }
}

tasks.register("removeClasses") {
    doLast {
        File("$rootDir/sdk/src/main/java/com/fingerprint/v4/model").deleteRecursively()
        File("$rootDir/sdk/src/main/java/com/fingerprint/v4/api").deleteRecursively()
    }
}

tasks.register<Copy>("copyOpenApiGeneratorIgnore") {
    from("$rootDir/.openapi-generator-ignore")
    into(layout.buildDirectory.dir("generated"))
}

tasks.register("copyGeneratedArtifacts") {
    finalizedBy("format")
    doLast {
        copy {
            from(layout.buildDirectory.dir("generated/docs"))
            into("$rootDir/docs")
        }

        copy {
            from(layout.buildDirectory.dir("generated/src/main/java"))
            into("$rootDir/sdk/src/main/java")
        }

        copy {
            from(layout.buildDirectory.file("generated/README.md"))
            into("$rootDir/")
        }
    }
}

tasks.openApiGenerate {
    dependsOn("removeDocs", "removeClasses", "copyOpenApiGeneratorIgnore")
    finalizedBy("copyGeneratedArtifacts")
}

tasks.compileJava {
    dependsOn("openApiGenerate")
}

tasks.test {
    useJUnitPlatform()
}

tasks.jar {
    archiveBaseName = "java-sdk"
}
