plugins {
    alias(libs.plugins.kitties.android.application)
    alias(libs.plugins.kitties.android.application.compose)
    alias(libs.plugins.kitties.android.hilt)
    alias(libs.plugins.com.google.dagger.hilt.android)
}

android {
    namespace = "com.dfcruz.kitties"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.dfcruz.kitties"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(projects.feature.cats)
    implementation(projects.feature.details)
    implementation(projects.feature.favourites)

    implementation(projects.core.model)
    implementation(projects.core.designsystem)
    implementation(projects.core.ui)

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
    
}