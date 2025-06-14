dependencies {
    implementation(libs.spring.validation)
    implementation(libs.spring.boot.starter.web)
    implementation(libs.jackson.kotlin)
    implementation(libs.spring.actuator)
    api(project(":security"))
    testImplementation(libs.spring.boot.starter.test)
}
