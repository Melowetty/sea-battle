dependencies {
    implementation(libs.spring.boot.starter)
    implementation(project(":data"))
    implementation(project(":common"))
    api(project(":game-core"))
}
