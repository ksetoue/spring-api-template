import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.0"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("org.asciidoctor.convert") version "1.5.8"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    kotlin("plugin.jpa") version "1.6.21"
    id("org.jlleitschuh.gradle.ktlint") version "10.3.0"
}

group = "com.ksetoue"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

extra["snippetsDir"] = file("build/generated-snippets")
extra["springCloudVersion"] = "2021.0.3"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.springframework.cloud:spring-cloud-starter-sleuth")
    implementation("org.springframework.session:spring-session-core")
    implementation("org.postgresql:postgresql:42.5.0")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.4")
    implementation("org.springframework.boot:spring-boot-starter-security:2.7.3")
    implementation("io.jsonwebtoken:jjwt-root:0.11.5")
    implementation("io.jsonwebtoken:jjwt:0.9.1")
    implementation("io.jsonwebtoken:jjwt-api:0.11.5")
    implementation("org.springframework.security:spring-security-test:5.7.3")
    implementation("org.springdoc:springdoc-openapi-core:1.1.49")
    implementation("org.springdoc:springdoc-openapi-ui:1.6.11")
    implementation("org.springdoc:springdoc-openapi-kotlin:1.6.11")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    implementation("org.springframework.boot:spring-boot-starter-test:2.7.3")
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
