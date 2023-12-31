plugins {
    id("com.android.application") version Versions.gradleVersion apply false
    id("com.android.library") version Versions.gradleVersion apply false
    id("org.jetbrains.kotlin.android") version Versions.kotlinVersion apply false
    id("org.jlleitschuh.gradle.ktlint") version Versions.ktlinVersion apply false
    id("com.google.dagger.hilt.android") version Versions.hiltVersion apply false
    id("com.google.gms.google-services") version Versions.googleServicesVersion apply false
    id("com.google.protobuf") version Versions.protobufVersion apply false
}
