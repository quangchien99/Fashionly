object Versions {
    const val appVersionCode = 1_000_000

    /*
    Project
     */
    const val kotlinVersion = "1.7.0"
    const val gradleVersion = "7.4.0"
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    const val androidCompileSdkVersion = 34
    const val targetSdkVersion = 34
    const val minSdkVersion = 24


    /*
    Android
     */
    const val coreKtxVersion = "1.12.0"
    const val lifecycleVersion = "2.6.2"
    const val appCompat = "1.6.1"

    /*
    Material
     */
    const val materialVersion = "1.2.0-alpha11"

    /*
    Compose
     */
    const val composeVersion = "1.5.4"
    const val activityComposeVersion = "1.8.1"
    const val kotlinCompilerExtensionVersion = "1.2.0"


    /*
    3rd libraries
     */
    const val ktlinVersion = "11.1.0"

    /*
    Testing
     */
    const val junitVersion = "4.13.2"
    const val testExtJunitVersion = "1.1.5"
    const val espressoVersion = "3.5.1"


    /*
    Utils
     */
    fun generateVersionName(): String {
        val patch: Int = appVersionCode.rem(1000)
        val minor: Int = (appVersionCode / 1000).rem(1000)
        val major: Int = (appVersionCode / 1000000).rem(1000)

        return "$major.$minor.$patch"
    }
}