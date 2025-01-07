plugins {
    id("tanorami.android.application")
}

android {
    namespace = "com.example.tanorami"

    defaultConfig {
        applicationId = "com.example.tanorami"
        versionCode = 4
        versionName = "1.0.3"
        testInstrumentationRunner = "com.example.tanorami.AppTestRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }
}

dependencies {
    implementation(libs.androidx.runtime)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.foundation)
    implementation(libs.androidx.foundation.layout)
    implementation(libs.androidx.ui.tooling)
    implementation(libs.kotlinx.serialization.core)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity.compose)

    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    //OkHttp
    implementation(platform(libs.okhttp.bom))
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    //Dagger2
    implementation(libs.dagger)
    ksp(libs.dagger.compiler)

    //Compose
    implementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.androidx.material)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    //Coil
    implementation(libs.coil.compose)

    //SplashScreen
    implementation(libs.androidx.core.splashscreen)

    //DataStore
    implementation(libs.androidx.datastore.preferences.core)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.espresso.contrib)
    androidTestImplementation(libs.dagger)

    implementation(project(":core:data"))
    implementation(project(":core:data-local"))
    implementation(project(":core:data-remote"))
    implementation(project(":core:domain"))
    implementation(project(":core:ui-components"))
    implementation(project(":core:ui-theme"))
    implementation(project(":core:resources"))
    implementation(project(":core:utils"))
    implementation(project(":core:base"))
    implementation(project(":core:models:common"))
    implementation(project(":feature:heroes-list"))
    implementation(project(":feature:weapons-list"))
    implementation(project(":feature:teams-and-builds"))
    implementation(project(":feature:profile"))
}