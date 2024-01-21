plugins {
    alias(libs.plugins.kitties.android.library)
}

android {
    namespace = "com.dfcruz.testing"
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.model)
    implementation(projects.core.network)
    implementation(projects.core.database)

    api(libs.junit)
    api(libs.kotlinx.coroutines.test)

}