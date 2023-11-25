plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jlleitschuh.gradle.ktlint")
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

    buildFeatures {
        compose = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.kotlinCompilerExtensionVersion
    }
    packagingOptions {
        exclude("META-INF/LGPL2.1")
    }
}

dependencies {

    implementation(Android.android_core_ktx)
    implementation(Android.lifecycle_runtime_ktx)
    implementation(Android.activity_compose)
    implementation(Android.compose_ui)
    implementation(Android.compose_ui_tooling_preview)
    implementation(Android.material_3)
    testImplementation(Testing.junit)
    androidTestImplementation(Testing.test_ext_junit)
    androidTestImplementation(Testing.espresso_core)
    androidTestImplementation(Testing.compose_ui_test)
    debugImplementation(Android.compose_ui_tooling)
}
