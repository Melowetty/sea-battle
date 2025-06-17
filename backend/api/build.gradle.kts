dependencies {
    implementation(libs.jackson.kotlin)
    api(project(":data"))
    api(project(":match-making"))
    testImplementation(libs.spring.boot.starter.test)
}
