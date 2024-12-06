plugins {
    id("tanorami.android.library.compose")
    id("tanorami.android.feature")
}

android {
    namespace = "com.example.teams_and_builds"
}

dependencies {
    implementation(project(":core:resources"))
    implementation(project(":core:data-remote"))
    implementation(project(":core:data-local"))
    implementation(project(":core:ui-components"))
    implementation(project(":core:models:common"))

    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.espresso.core)
}