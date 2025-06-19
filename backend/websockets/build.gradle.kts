dependencies {
    implementation(libs.spring.validation)
    implementation(libs.spring.websocket)
    implementation(libs.jackson.kotlin)
    api(project(":security"))
    testImplementation(libs.spring.boot.starter.test)
}
