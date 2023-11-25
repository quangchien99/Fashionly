object BuildPlugins {
    const val gradle = "com.android.tools.build:gradle:${Versions.gradleVersion}"
    const val kotlin_gradle_plugin = "gradle-plugin"
}

object Android {
    const val android_core_ktx = "androidx.core:core-ktx:${Versions.coreKtxVersion}"
    const val app_compat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val lifecycle_runtime_ktx =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleVersion}"

    const val activity_compose =
        "androidx.activity:activity-compose:${Versions.activityComposeVersion}"
    const val compose_ui = "androidx.compose.ui:ui:${Versions.composeVersion}"
    const val compose_ui_tooling_preview =
        "androidx.compose.ui:ui-tooling-preview:${Versions.composeVersion}"
    const val compose_ui_tooling = "androidx.compose.ui:ui-tooling:${Versions.composeVersion}"

    const val material_3 = "androidx.compose.material3:material3:${Versions.materialVersion}"
}

object Testing {
    const val junit = "junit:junit:${Versions.junitVersion}"
    const val test_ext_junit = "androidx.test.ext:junit:${Versions.testExtJunitVersion}"
    const val espresso_core = "androidx.test.espresso:espresso-core:${Versions.espressoVersion}"
    const val compose_ui_test = "androidx.compose.ui:ui-test-junit4:${Versions.composeVersion}"
}

object Libraries {

}