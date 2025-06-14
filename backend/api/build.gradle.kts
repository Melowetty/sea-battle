dependencies {
    implementation(libs.jackson.kotlin)
    api(project(":data"))
    testImplementation(libs.spring.boot.starter.test)
}
