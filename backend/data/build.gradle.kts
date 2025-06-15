plugins {
    alias(libs.plugins.spring.jpa)
    alias(libs.plugins.spring.dependency.management)
}

dependencies {
    api(libs.spring.boot.starter)
    api(libs.spring.boot.starter.data.jpa)
    implementation(libs.liquibase.core)
    implementation(project(":common"))
    runtimeOnly(libs.postgresql)
    testImplementation(libs.spring.boot.starter.test)
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")

}
