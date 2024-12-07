plugins {
    id("tanorami.android.library.compose")
    id("tanorami.android.feature")
}

android {
    namespace = "com.example.heroes_list"
}

dependencies {
    implementation(project(":core:ui-components"))
    implementation(project(":core:ui-theme"))
    implementation(project(":core:models:common"))
    implementation(project(":core:domain"))

    //Compose
    implementation(libs.foundation)
    implementation(libs.foundation.layout)
    implementation(libs.androidx.ui)

    //Coil
    implementation(libs.coil.compose)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.espresso.core)
}