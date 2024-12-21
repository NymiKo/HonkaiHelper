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
    implementation(project(":core:ui-theme"))
    implementation(project(":core:models:common"))
    implementation(project(":core:domain"))

    implementation(libs.androidx.material)

    //Paging
    implementation(libs.androidx.paging.runtime)
    implementation(libs.androidx.paging.compose)
    implementation(libs.androidx.material.icons.extended)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.espresso.core)
}