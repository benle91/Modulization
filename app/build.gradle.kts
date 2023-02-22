plugins {
    id(AppConfig.PluginsDependencies.androidApplication)
    id(AppConfig.PluginsDependencies.kotlinAndroid)
    id(AppConfig.PluginsDependencies.kotlinKapt)
    id(AppConfig.PluginsDependencies.paranoid)
    id(AppConfig.PluginsDependencies.googleServices)
}
println("This is executed during the configuration phase.")

android {
    compileSdkVersion(AppConfig.compileSdk)

    defaultConfig {
        applicationId = "com.android.modulization"
        minSdkVersion(AppConfig.minSdk)
        targetSdkVersion(AppConfig.targetSdk)
        versionCode = (System.currentTimeMillis() / 60000).toInt()
        versionName = "1.0"
        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        named(AppConfig.BuildTypes.debug) {
            keyAlias = AppConfig.SigningConfigsDebug.keyAlias
            keyPassword = AppConfig.SigningConfigsDebug.keyPassword
            storeFile = rootProject.file(AppConfig.SigningConfigsDebug.storeFile)
            storePassword = AppConfig.SigningConfigsDebug.storePassword
        }
    }


    buildTypes {
        named(AppConfig.BuildTypes.release) {
            isMinifyEnabled = true
            setProguardFiles(
                listOf(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            )
        }
        named(AppConfig.BuildTypes.debug) {
            signingConfig = signingConfigs.getByName(AppConfig.BuildTypes.debug)
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class).configureEach {
        kotlinOptions {
            // Enable experimental coroutines APIs, including Flow
            freeCompilerArgs += "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
            freeCompilerArgs += "-Xopt-in=kotlinx.coroutines.FlowPreview"
            freeCompilerArgs += "-Xopt-in=kotlin.Experimental"
        }
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(platform("com.google.firebase:firebase-bom:31.2.2"))
    implementation(Dependencies.libDefault)
    implementation(Dependencies.libUI)
    testImplementation(Dependencies.libUnitTest)
    androidTestImplementation(Dependencies.libAndroidTest)
    kapt(Dependencies.kotlinGradlePlugin)
    implementation(project(AppConfig.Modules.CoreUI))
    implementation(project(AppConfig.Features.FeatureFirst))
    implementation(project(AppConfig.Features.FeatureSecond))
    implementation(project(AppConfig.Features.FeatureAuth))
}

