object BuildPlugins {
    const val gradle = "com.android.tools.build:gradle:${Versions.gradleVersion}"
    const val kotlin_gradle_plugin = "gradle-plugin"
}

object Android {
    const val android_core_ktx = "androidx.core:core-ktx:${Versions.coreKtxVersion}"
    const val app_compat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"
    const val lifecycle_runtime_ktx =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleVersion}"
    const val lifecycle_viewmodel_compose =
        "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifecycleVersion}"

    const val activity_compose =
        "androidx.activity:activity-compose:${Versions.activityComposeVersion}"
    const val hilt_navigation_compose =
        "androidx.hilt:hilt-navigation-compose:${Versions.hiltNavigationComposeVersion}"
    const val navigation_compose =
        "androidx.navigation:navigation-compose:${Versions.navigationComposeVersion}"
    const val compose_ui = "androidx.compose.ui:ui:${Versions.composeVersion}"
    const val compose_ui_tooling_preview =
        "androidx.compose.ui:ui-tooling-preview:${Versions.composeVersion}"
    const val compose_ui_tooling = "androidx.compose.ui:ui-tooling:${Versions.composeVersion}"
    const val compose_foundation =
        "androidx.compose.foundation:foundation:${Versions.composeFoundationVersion}"

    const val material_3 = "androidx.compose.material3:material3:${Versions.materialVersion}"
    const val datastore_references =
        "androidx.datastore:datastore-preferences:${Versions.dataStorePreferencesVersion}"
    const val protobuf_javalite =
        "com.google.protobuf:protobuf-javalite:${Versions.protobufJavaliteVersion}"
    const val protobuf_protoc =
        "com.google.protobuf:protoc:${Versions.protobufJavaliteVersion}"
    const val compose_live_data =
        "androidx.compose.runtime:runtime-livedata:${Versions.composeVersion}"
}

object Testing {
    const val junit = "junit:junit:${Versions.junitVersion}"
    const val test_ext_junit = "androidx.test.ext:junit:${Versions.testExtJunitVersion}"
    const val espresso_core = "androidx.test.espresso:espresso-core:${Versions.espressoVersion}"
    const val compose_ui_test = "androidx.compose.ui:ui-test-junit4:${Versions.composeVersion}"
}

object Libraries {
    const val lottie_compose = "com.airbnb.android:lottie-compose:${Versions.lottieComposeVersion}"
    const val dagger_hilt_android = "com.google.dagger:hilt-android:${Versions.hiltVersion}"
    const val dagger_hilt_compiler = "com.google.dagger:hilt-compiler:${Versions.hiltVersion}"
    const val coil_compose = "io.coil-kt:coil-compose:${Versions.coilVersion}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    const val retrofit_converter_gson =
        "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}"
    const val gson = "com.google.code.gson:gson:${Versions.gsonVersion}"
    const val logging_interceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.okhttpLoggingVersion}"

    const val firebase_bom = "com.google.firebase:firebase-bom:${Versions.firebaseBomVersion}"
    const val firebase_analytics = "com.google.firebase:firebase-analytics"
    const val firebase_remote_config = "com.google.firebase:firebase-config"
    const val firebase_storage = "com.google.firebase:firebase-storage-ktx"
    const val coroutines_play_services =
        "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Versions.coroutinesPlayServicesVersion}"
    const val kotlinx_coroutines_core =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinxCoroutinesCoreVersion}"
    const val room_runtime = "androidx.room:room-runtime:${Versions.roomVersion}"
    const val room_compiler = "androidx.room:room-compiler:${Versions.roomVersion}"
    const val room_ktx = "androidx.room:room-ktx:${Versions.roomVersion}"
}