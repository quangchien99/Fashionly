plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jlleitschuh.gradle.ktlint")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "chn.phm.domain"
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
}

dependencies {
    implementation(Android.android_core_ktx)
    implementation(Android.app_compat)
    implementation(Android.material_3)
    testImplementation(Testing.junit)
    androidTestImplementation(Testing.test_ext_junit)
    androidTestImplementation(Testing.espresso_core)

    // Hilt
    implementation(Libraries.dagger_hilt_android)
    kapt(Libraries.dagger_hilt_compiler)
}

kapt {
    correctErrorTypes = true
}
