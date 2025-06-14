dependencies {
    implementation(libs.jwt.api)
    implementation(libs.spring.validation)
    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.websocket)
    implementation(libs.jackson.kotlin)
    implementation(libs.spring.security)
    implementation(project(":common"))
    api(project(":api"))
    runtimeOnly(libs.jwt.impl)
    runtimeOnly(libs.jwt.jackson)
    testImplementation(libs.spring.boot.starter.test)
}