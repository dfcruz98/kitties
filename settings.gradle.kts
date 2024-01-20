pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Kitties"
include(":app")
include(":core:network")
include(":core:database")
include(":core:data")
include(":core:model")
include(":feature:details")
include(":feature:favourites")
include(":feature:cats")
include(":core:designsystem")
include(":core:ui")
