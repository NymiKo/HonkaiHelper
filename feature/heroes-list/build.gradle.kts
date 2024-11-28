plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.example.heroes_list"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.kotlinx.serialization.json)

    //Dagger2
    implementation(libs.dagger)
    ksp(libs.dagger.compiler)

    //Compose
    implementation(libs.runtime)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.foundation)
    implementation(libs.foundation.layout)
    implementation(libs.androidx.material)
    implementation(libs.androidx.material3)
    implementation(libs.ui)
    implementation(libs.androidx.navigation.compose)

    //Coil
    implementation(libs.coil.compose)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.espresso.core)

    implementation(project(":core:base"))
    implementation(project(":core:domain"))
    implementation(project(":core:di"))
    implementation(project(":core:ui-theme"))
    implementation(project(":core:ui-components"))
}