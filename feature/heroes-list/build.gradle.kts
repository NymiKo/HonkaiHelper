plugins {
    id("tanorami.android.library.compose")
    id("tanorami.android.feature")
}

android {
    namespace = "com.example.heroes_list"
}

dependencies {
    implementation(project(":core:ui-components"))

    implementation(libs.material)

    //Compose
    implementation(libs.foundation)
    implementation(libs.foundation.layout)
    implementation(libs.androidx.ui)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.espresso.core)
}