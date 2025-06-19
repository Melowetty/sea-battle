dependencies {
    implementation(libs.spring.boot.starter)
    implementation(libs.spring.boot.starter.data.jpa)
    implementation(libs.liquibase.core)
    implementation(libs.jackson.kotlin)
    implementation(project(":data"))
    implementation(project(":common"))
    implementation(project(":bot-engine"))
    runtimeOnly(libs.postgresql)
    testImplementation(libs.spring.boot.starter.test)
}
