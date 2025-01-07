plugins {
    id("tanorami.android.library.compose")
    id("tanorami.android.feature")
}

android {
    namespace = "com.example.profile"
}

dependencies {
    implementation(project(":core:resources"))
    implementation(project(":core:models:common"))
    implementation(project(":core:data-local"))
    implementation(project(":core:data-remote"))
    implementation(project(":core:domain"))
    implementation(project(":core:utils"))
    implementation(project(":core:ui-components"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    //OkHttp
    implementation(libs.okhttp)

    //Coil
    implementation(libs.coil.compose)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.espresso.core)
}