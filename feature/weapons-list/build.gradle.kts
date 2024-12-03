plugins {
    id("tanorami.android.library.compose")
    id("tanorami.android.feature")
}

android {
    namespace = "com.example.weapons_list"
}

dependencies {
    implementation(project(":core:data-local"))
    implementation(project(":core:strings"))
    implementation(project(":core:ui-components"))

    implementation(libs.material)

    //Compose
    implementation(libs.androidx.material.icons.extended)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.espresso.core)
}