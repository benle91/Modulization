plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}


gradlePlugin {
    plugins {
        register("dynamic-feature-plugin") {
            id = "dynamic-feature-plugin"
            implementationClass = "DynamicFeaturePlugin"
        }
        register("library-plugin") {
            id = "library-plugin"
            implementationClass = "LibraryPlugin"
        }
    }
}

dependencies {
    compileOnly(gradleApi())
    implementation(kotlin("gradle-plugin", version = "1.5.0"))
    implementation("com.android.tools.build:gradle:7.3.1")
    implementation("com.google.gms:google-services:4.3.15")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.21")
    implementation("org.jetbrains.kotlin:kotlin-serialization:1.7.21")
    implementation("io.michaelrocks:paranoid-gradle-plugin:0.3.7")
    implementation("org.jacoco:org.jacoco.core:0.8.7")

}

buildscript {
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.21")
    }
}