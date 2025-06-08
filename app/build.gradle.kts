plugins {
    id("java")
    id("application")
    id("com.github.ben-manes.versions") version "0.52.0"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

application {
    mainClass.set("hexlet.code.App")
}

tasks.jar {
    manifest {
        attributes(
                "Main-Class" to "hexlet.code.App"
        )
    }
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.apache.commons:commons-lang3:3.17.0")
    implementation("info.picocli:picocli:4.7.7")
    annotationProcessor("info.picocli:picocli-codegen:4.7.7")
    implementation ("com.fasterxml.jackson.core:jackson-databind:2.13.3")
    implementation ("commons-io:commons-io:2.11.0")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.13.3")
}

tasks.test {
    useJUnitPlatform()
}