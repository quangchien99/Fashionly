plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jlleitschuh.gradle.ktlint")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "chn.phm.presentation"
    compileSdk = Versions.androidCompileSdkVersion

    defaultConfig {
        minSdk = Versions.minSdkVersion

        testInstrumentationRunner = Versions.testInstrumentationRunner
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = true
        viewBinding = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.kotlinCompilerExtensionVersion
    }
}

dependencies {
    implementation(Android.android_core_ktx)
    implementation(Android.app_compat)
    implementation(Android.activity_compose)
    implementation(Android.compose_ui_tooling_preview)
    implementation(Android.compose_foundation)
    implementation(Android.lifecycle_viewmodel_compose)
    implementation(Android.hilt_navigation_compose)
    implementation(Android.material_3)
    implementation(Android.navigation_compose)

    testImplementation(Testing.junit)
    androidTestImplementation(Testing.test_ext_junit)
    androidTestImplementation(Testing.espresso_core)

    // Lottie
    implementation(Libraries.lottie_compose)

    // Coil
    implementation(Libraries.coil_compose)

    // Hilt
    implementation(Libraries.dagger_hilt_android)
    kapt(Libraries.dagger_hilt_compiler)

    implementation(project(":domain"))

    implementation(Android.lifecycle_runtime_ktx)
    implementation(Android.compose_live_data)
}

kapt {
    correctErrorTypes = true
}
