val projectVersion: String by project

group = "com.github.fingerprintjs"
version = projectVersion

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
    groupId.set("com.fingerprint")
    id.set("java-sdk")
    version.set(projectVersion)
    apiPackage.set("com.fingerprint.api")
    modelPackage.set("com.fingerprint.model")
    invokerPackage.set("com.fingerprint.sdk")
    library.set("jersey3")
    templateDir.set("$rootDir/template")

    gitHost.set("github.com")
    gitRepoId.set("java-sdk")
    gitUserId.set("fingerprintjs")
    configOptions.put("hideGenerationTimestamp", "true")
    configOptions.put("openApiNullable", "false")
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
        File("$rootDir/sdk/src/main/java/com/fingerprint/model").deleteRecursively()
        File("$rootDir/sdk/src/main/java/com/fingerprint/api").deleteRecursively()
    }
}

tasks.register<Copy>("copyOpenApiGeneratorIgnore") {
    from("$rootDir/.openapi-generator-ignore")
    into(layout.buildDirectory.dir("generated"))
}

tasks.register<Copy>("copyDocs") {
    from(layout.buildDirectory.dir("generated/docs"))
    into("$rootDir/docs")
    dependsOn(tasks.openApiGenerate)
}

tasks.register<Copy>("copyClasses") {
    from(layout.buildDirectory.dir("generated/src/main/java"))
    into("src/main/java")
    dependsOn(tasks.openApiGenerate)
    dependsOn("copyReadme")
}

tasks.register<Copy>("copyReadme") {
    from(file(layout.buildDirectory.file("generated/README.md")))
    into(file("$rootDir/"))
    dependsOn("copyDocs")
}

tasks.openApiGenerate {
    dependsOn("removeDocs", "removeClasses", "copyOpenApiGeneratorIgnore")
}

tasks.named("build") {
    finalizedBy("copyReadme")
}

tasks.compileJava {
    dependsOn("copyClasses")
}

tasks.test {
    useJUnitPlatform()
}

tasks.jar {
    archiveBaseName = "java-sdk"
}
