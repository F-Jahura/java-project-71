plugins {
    id("java")
    application
    id("com.github.ben-manes.versions") version "0.52.0"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("org.sonarqube") version "6.2.0.5505"
    jacoco
    checkstyle
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
    implementation ("com.fasterxml.jackson.core:jackson-databind:2.13.4.2")
    implementation ("commons-io:commons-io:2.14.0")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.13.3")
}

tasks.test {
    useJUnitPlatform()
}

checkstyle {
    toolVersion = "9.0"
    configDirectory.set(file("config/checkstyle"))
}

tasks.withType<Checkstyle>().configureEach {
    classpath = files("${project.rootDir}/src/test/java")
}

val myCheckstyleTest by tasks.registering(Checkstyle::class) {
    source("src/test/java")
    classpath = files()
    configFile = file("${project.rootDir}/config/checkstyle/checkstyle.xml")
    include("**/*.java")
    exclude("**/generated/**")
}

tasks.named("check") {
    dependsOn(myCheckstyleTest)
}

tasks.register("taskName") {
    doLast {
        println("This is a new custom task!")
    }
}

sonar {
    properties {
        property("sonar.projectKey", "F-Jahura_java-project-71")
        property("sonar.organization", "f-jahura")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}