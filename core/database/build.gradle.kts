plugins {
    alias(libs.plugins.kitties.android.library)
    alias(libs.plugins.kitties.android.hilt)
    kotlin("kapt")
}

kapt {
    correctErrorTypes = true
}

android {
    namespace = "com.dfcruz.database"

    defaultConfig {

        // Add support for Room schema export
        kapt {
            arguments {
                arg("room.schemaLocation", "$projectDir/schemas")
            }
        }
    }

}

dependencies {

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    annotationProcessor(libs.room.compiler)
    ksp(libs.room.compiler)
}