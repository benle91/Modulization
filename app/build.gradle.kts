plugins {
    id(AppConfig.PluginsDependencies.androidApplication)
    id(AppConfig.PluginsDependencies.kotlinAndroid)
    id(AppConfig.PluginsDependencies.kotlinKapt)
    id(AppConfig.PluginsDependencies.paranoid)
}
println("This is executed during the configuration phase.")

android {
    namespace = AppConfig.namespace
    compileSdkVersion(AppConfig.compileSdk)

    defaultConfig {
        applicationId = "com.android"
        minSdkVersion(AppConfig.minSdk)
        targetSdkVersion(AppConfig.targetSdk)
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        dataBinding = true
    }

    buildTypes {
        named("release") {
            isMinifyEnabled = true
            setProguardFiles(
                listOf(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            )
        }
        named("debug") {
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
    packagingOptions {
        pickFirst("META-INF/*")
        exclude("build-data.properties")
    }
    //dynamicFeatures += setOf(":features:dynamicfeature")
    tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class).configureEach {
        kotlinOptions {
            // Enable experimental coroutines APIs, including Flow
            freeCompilerArgs += "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
            freeCompilerArgs += "-Xopt-in=kotlinx.coroutines.FlowPreview"
            freeCompilerArgs += "-Xopt-in=kotlin.Experimental"
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Dependencies.libDefault)
    implementation(Dependencies.libUI)
    testImplementation(Dependencies.libUnitTest)
    androidTestImplementation(Dependencies.libAndroidTest)
    kapt(Dependencies.kotlinGradlePlugin)
    api(project(":domain"))
}

