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
    const val appCompatVersion = "1.6.1"

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
    const val composeFoundationVersion = "1.5.4"
    const val dataStorePreferencesVersion = "1.0.0"
    const val navigationCompose = "2.7.5"

    /*
    3rd libraries
     */
    const val ktlinVersion = "11.1.0"
    const val lottieComposeVersion = "5.2.0"
    const val hiltVersion = "2.44"
    const val hiltNavigationComposeVersion = "1.0.0"
    const val coilVersion = "2.2.2"
    const val retrofitVersion = "2.9.0"
    const val gsonVersion = "2.9.1"
    const val okhttpLoggingVersion = "5.0.0-alpha.5"

    /*
    Firebase
     */
    const val googleServicesVersion = "4.3.14"
    const val firebaseBomVersion = "32.3.0"
    const val coroutinesPlayServicesVersion = "1.7.1"

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