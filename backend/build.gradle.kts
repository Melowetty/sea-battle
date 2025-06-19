plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.spring)
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)

}

group = "ru.sigma"
version = "0.0.1-SNAPSHOT"

dependencies {
    implementation(libs.spring.boot.starter)
    implementation(project(":data"))
    implementation(project(":security"))
    implementation(project(":api"))
    implementation(project(":game-core"))
    implementation(project(":match-making"))
    implementation(project(":common"))
    implementation(project(":web"))
    implementation(project(":bot-engine"))
}

allprojects {
    repositories {
        mavenCentral()
        google()
    }
}

subprojects {
    plugins.apply("org.jetbrains.kotlin.jvm")
    plugins.apply("org.jetbrains.kotlin.kapt")
    plugins.apply("org.jetbrains.kotlin.plugin.spring")
    plugins.apply("io.spring.dependency-management")

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(21))
        }
    }

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter")
        implementation("org.jetbrains.kotlin:kotlin-stdlib")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        testImplementation("org.jetbrains.kotlin:kotlin-test")
        testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

tasks.jar {
    enabled = false
}
