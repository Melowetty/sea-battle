dependencies {
    implementation(libs.jwt.api)
    implementation(libs.spring.validation)
    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.websocket)
    implementation(libs.jackson.kotlin)
    runtimeOnly(libs.jwt.api)
    runtimeOnly(libs.jwt.jackson)
    testImplementation(libs.spring.boot.starter.test)
}