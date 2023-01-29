import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.DynamicFeatureExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.repositories

class DynamicFeaturePlugin: Plugin<Project> {
    override fun apply(project: Project) {
        //apply default required plugins
        project.run {
            plugins.run {
                apply(AppConfig.PluginsDependencies.androidLibrary)
                apply(AppConfig.PluginsDependencies.kotlinAndroid)
                apply(AppConfig.PluginsDependencies.kotlinKapt)
                apply(AppConfig.PluginsDependencies.kotlinParcelize)
            }

            // Configure common android build parameters.
            extensions.getByName("android").run {
                if(this is LibraryExtension) {
                    compileSdkVersion(AppConfig.compileSdk)
                    defaultConfig {
                        minSdkVersion(AppConfig.minSdk)
                        testInstrumentationRunner = AppConfig.androidTestInstrumentation
                        proguardFile(AppConfig.proguardRules)
                    }

                    compileOptions {
                        sourceCompatibility = JavaVersion.VERSION_1_8
                        targetCompatibility = JavaVersion.VERSION_1_8
                    }

                    buildFeatures {
                        viewBinding = true
                    }

                    //init dynamic source sets
                    val sources = listOf(
                        "src/main/res",
                        "src/main/res/layouts",
                        "src/main/res/xml",
                    )
                    sourceSets {
                        getByName("main").res.setSrcDirs(sources)
                    }

                    lint {
                        checkReleaseBuilds = false
                        disable.addAll(listOf("MissingTranslation"))
                        abortOnError = false
                    }
                }
            }
            apply {
                plugin(AppConfig.PluginsDependencies.kotlinAndroid)
            }
            dependencies {
                add("api", project(AppConfig.Modules.Domain))
                add("api", project(AppConfig.Modules.CoreUI))
                //add all default dependencies
                for(dp in Dependencies.libDefault) {
                    add("implementation", dp)
                }
                //add default android test dependencies
                for(dp in Dependencies.libAndroidTest) {
                    add("androidTestImplementation", dp)
                }
                //add default test dependencies
                for(dp in Dependencies.libUnitTest) {
                    add("testImplementation", dp)
                }
            }

        }
    }
}