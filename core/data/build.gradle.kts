plugins {
    alias(libs.plugins.kitties.android.library)
    alias(libs.plugins.kitties.android.hilt)
}

android {
    namespace = "com.dfcruz.data"
}

dependencies {

    implementation(projects.core.network)
    implementation(projects.core.database)
    implementation(projects.core.model)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}