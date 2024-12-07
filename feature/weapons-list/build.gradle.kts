plugins {
    id("tanorami.android.library.compose")
    id("tanorami.android.feature")
}

android {
    namespace = "com.example.weapons_list"
}

dependencies {
    implementation(project(":core:data-local"))
    implementation(project(":core:resources"))
    implementation(project(":core:ui-components"))
    implementation(project(":core:models:common"))
    implementation(project(":core:ui-theme"))

    //Coil
    implementation(libs.coil.compose)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.espresso.core)
}