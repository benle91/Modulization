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

    private const val lifecycleLiveDataKTX =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleLiveDataKTX}"

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

    /**
     * room
     * */
    private const val roomRuntime = "androidx.room:room-runtime:${Versions.roomVersion}"
    private const val roomKTX = "androidx.room:room-ktx:${Versions.roomVersion}"
    // annotationProcessor
    private const val annotationProcessorRoom = "androidx.room:room-compiler:${Versions.roomVersion}"
    // kapt
    private const val kaptRoom = "androidx.room:room-compiler:${Versions.roomVersion}"

    private const val playCore = "com.google.android.play:core:${Versions.playCore}"
    private const val playCoreKtx = "com.google.android.play:core-ktx:${Versions.playCore}"

    /**
     * encrypt share preferences
     * */
    private const val securityCryptoKTX = "androidx.security:security-crypto-ktx:1.1.0-alpha04"
    private const val securityIdentityCredential = "androidx.security:security-identity-credential:1.0.0-alpha03"
    private const val securityAppAuthenticator = "androidx.security:security-app-authenticator:1.0.0-alpha02"

    val libDefault = listOf<String>(
        coreKTX, appcompat, koin, playCore, playCoreKtx
    )

    val libUI = listOf<String>(
        koinNavigation, material, constraintlayout, navigationFragmentKTX, navigationUIKTX, lifecycleLiveDataKTX
    )

    val libUnitTest = listOf<String>(junit)
    val libAndroidTest = listOf<String>(androidJunit, androidEspressoCore)
    val libNetwork = listOf<String>(retrofit, retrofitConverterGson, loggingInterceptor)
    val libRoom = listOf<String>(roomRuntime, roomKTX)
    val libKapt = listOf<String>(kaptRoom)
    val libAnnotation = listOf<String>(annotationProcessorRoom)
    val libData = listOf<String>(retrofitConverterGson, roomRuntime)
    val libPreference = listOf<String>(securityCryptoKTX, securityIdentityCredential, securityAppAuthenticator)
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


