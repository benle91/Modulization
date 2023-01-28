# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.kts.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
# Keep classes and class members annotated by the @Keep annotation.
# @Keep specifies not to shrink, optimize, or obfuscate the annotated class
# or class member as an entry point.

-keep @androidx.annotation.Keep class *

-keepclassmembers class * {
    @androidx.annotation.Keep *;
}

# Ignore warnings from third party libraries.
-dontwarn com.**
-dontwarn net.**
-dontwarn org.**
-dontwarn android.net.**

# For now, we're not obfuscating, because that can get confusing.
# https://stackoverflow.com/questions/9651703/using-proguard-with-android-without-obfuscation
-dontobfuscate
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*,!code/allocation/variable

-optimizationpasses 5

-dontwarn okhttp3.**
# https://www.guardsquare.com/en/products/proguard/manual/troubleshooting#unresolvedclass
-keep,includedescriptorclasses class *
# https://github.com/google/dagger/issues/645
-dontwarn dagger.releasablereferences.*