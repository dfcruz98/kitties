import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.kitties.android.library)
    alias(libs.plugins.kitties.android.hilt)
}

android {
    namespace = "com.dfcruz.network"

    val apikeyPropertiesFile = rootProject.file("api.properties")
    val apikeyProperties = Properties()
    apikeyProperties.load(FileInputStream(apikeyPropertiesFile))

    defaultConfig {
        buildConfigField("String", "API_KEY", apikeyProperties["API_KEY"] as String)
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {

    testImplementation(libs.junit)
    testImplementation(libs.truth)
    testImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)
}