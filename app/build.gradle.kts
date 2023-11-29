plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jlleitschuh.gradle.ktlint")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "chn.phm.fashionly"
    compileSdk = Versions.androidCompileSdkVersion

    defaultConfig {
        applicationId = "chn.phm.fashionly"
        minSdk = Versions.minSdkVersion
        targetSdk = Versions.targetSdkVersion
        versionCode = Versions.appVersionCode
        versionName = Versions.generateVersionName()

        testInstrumentationRunner = Versions.testInstrumentationRunner

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        named("release") {
            isMinifyEnabled = true
            setProguardFiles(
                listOf(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
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

    packagingOptions {
        exclude("META-INF/LGPL2.1")
    }
}

dependencies {

    implementation(Android.android_core_ktx)
    implementation(Android.lifecycle_runtime_ktx)
    implementation(Android.compose_ui)
    implementation(Android.material_3)
    testImplementation(Testing.junit)
    androidTestImplementation(Testing.test_ext_junit)
    androidTestImplementation(Testing.espresso_core)
    androidTestImplementation(Testing.compose_ui_test)
    debugImplementation(Android.compose_ui_tooling)

    // Hilt
    implementation(Libraries.dagger_hilt_android)
    kapt(Libraries.dagger_hilt_compiler)

    // Gson
    implementation(Libraries.gson)

    // Retrofit
    implementation(Libraries.retrofit)
    implementation(Libraries.retrofit_converter_gson)

    implementation(project(":presentation"))
    implementation(project(":data"))
    implementation(project(":domain"))
}

kapt {
    correctErrorTypes = true
}
