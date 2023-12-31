import com.google.protobuf.gradle.generateProtoTasks
import com.google.protobuf.gradle.protobuf
import com.google.protobuf.gradle.protoc

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jlleitschuh.gradle.ktlint")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("com.google.protobuf")
}

android {
    namespace = "chn.phm.data"
    compileSdk = Versions.androidCompileSdkVersion

    defaultConfig {
        minSdk = Versions.minSdkVersion

        testInstrumentationRunner = Versions.testInstrumentationRunner
        consumerProguardFiles("consumer-rules.pro")
    }

    sourceSets["main"].java.srcDir("build/generated/source/proto/main/java")

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

protobuf {
    protoc {
        artifact = Android.protobuf_protoc
    }
    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                create("java") {
                    option("lite")
                }
            }
        }
    }
}

dependencies {
    implementation(Android.android_core_ktx)
    implementation(Android.app_compat)
    implementation(Android.material_3)
    testImplementation(Testing.junit)
    androidTestImplementation(Testing.test_ext_junit)
    androidTestImplementation(Testing.espresso_core)

    implementation(Android.datastore_references)

    // Hilt
    implementation(Libraries.dagger_hilt_android)
    kapt(Libraries.dagger_hilt_compiler)

    // Gson
    implementation(Libraries.gson)

    // Retrofit
    implementation(Libraries.retrofit)
    implementation(Libraries.retrofit_converter_gson)
    implementation(Libraries.logging_interceptor)

    // Firebase
    implementation(platform(Libraries.firebase_bom))
    implementation(Libraries.firebase_analytics)
    implementation(Libraries.firebase_remote_config)
    implementation(Libraries.coroutines_play_services)
    implementation(Libraries.firebase_storage)

    implementation(Libraries.kotlinx_coroutines_core)

    // Room components
    implementation(Libraries.room_runtime)
    annotationProcessor(Libraries.room_compiler)
    implementation(Libraries.room_ktx)
    kapt(Libraries.room_compiler)

    // Protobuf
    implementation(Android.protobuf_javalite)

    implementation(project(":domain"))
}

kapt {
    correctErrorTypes = true
}
