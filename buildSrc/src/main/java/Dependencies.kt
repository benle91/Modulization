import org.gradle.api.artifacts.dsl.DependencyHandler

object Dependencies {

    const val kotlinGradlePlugin =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinGradlePlugin}"
    const val kotlinSerialization =
        "org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlinSerialization}"
    const val paranoid = "io.michaelrocks:paranoid-gradle-plugin:${Versions.paranoid}"
    const val firebaseBom = "implementation platform('com.google.firebase:firebase-bom:${Versions.firebaseBom}')"
    const val ktlint = "com.pinterest:ktlint:${Versions.ktlint}"

    /**
     * lib
     * */
    private const val coreKTX = "androidx.core:core-ktx:${Versions.coreKTX}"
    private const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    private const val material = "com.google.android.material:material:${Versions.material}"
    private const val constraintlayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
    private const val navigationFragmentKTX =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigationFragmentKTX}"
    private const val navigationUIKTX =
        "androidx.navigation:navigation-ui-ktx:${Versions.navigationUIKTX}"

    /**
     * lib test
     * */
    private const val junit = "junit:junit:${Versions.junit}"
    private const val androidJunit = "androidx.test.ext:junit:${Versions.androidJunit}"
    private const val androidEspressoCore =
        "androidx.test.espresso:espresso-core:${Versions.androidEspressoCore}"

    private const val koin = "io.insert-koin:koin-android:${Versions.koin}"
    private const val koinNavigation = "io.insert-koin:koin-androidx-navigation:${Versions.koin}"
    private const val koinTest = "io.insert-koin:koin-test:${Versions.koin}"

    /**
     * retrofit
     * */
    private const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    private const val retrofitConverterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}"
    private const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.10"

    val libDefault = listOf<String>(
        coreKTX, appcompat, koin
    )

    val libUI = listOf<String>(
        koinNavigation, material, constraintlayout, navigationFragmentKTX, navigationUIKTX
    )

    val libUnitTest = listOf<String>(junit)
    val libAndroidTest = listOf<String>(androidJunit, androidEspressoCore)
    val libNetwork = listOf<String>(retrofit, retrofitConverterGson, loggingInterceptor)

}

//util functions for adding the different type dependencies from build.gradle file
fun DependencyHandler.kapt(list: List<String>) {
    list.forEach { dependency ->
        add("kapt", dependency)
    }
}

fun DependencyHandler.implementation(list: List<String>) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}

fun DependencyHandler.androidTestImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("androidTestImplementation", dependency)
    }
}

fun DependencyHandler.testImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("testImplementation", dependency)
    }
}

fun DependencyHandler.annotationProcessor(list: List<String>) {
    list.forEach { dependency ->
        add("annotationProcessor", dependency)
    }
}


