plugins {
    alias(libs.plugins.kitties.android.jvm.library)
}

dependencies {
    compileOnly(libs.compose.stable.marker)
}