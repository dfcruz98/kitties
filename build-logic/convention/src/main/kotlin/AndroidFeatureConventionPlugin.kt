import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            dependencies {
                add("implementation", project(":core:designsystem"))
                add("implementation", project(":core:ui"))
                add("implementation", project(":core:model"))
                add("implementation", project(":core:data"))
                add("implementation", project(":core:testing"))
            }
        }
    }
}