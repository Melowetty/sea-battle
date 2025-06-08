plugins {
    alias(libs.plugins.spring.jpa)
}

dependencies {
    implementation(libs.spring.boot.starter.data.jpa)
    implementation(libs.liquibase.core)
    runtimeOnly(libs.postgresql)
    testImplementation(libs.spring.boot.starter.test)
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.15.0")
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")

}
