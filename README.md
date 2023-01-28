# Modulization

### To avoid `BuildConfig is defined multiple times` exception:
Add this code to `gradle.properties`
```
# Disable build features that are enabled by default,
# https://developer.android.com/studio/releases/gradle-plugin#buildFeatures
android.defaults.buildfeatures.buildconfig=false
android.defaults.buildfeatures.aidl=false
android.defaults.buildfeatures.renderscript=false
android.defaults.buildfeatures.resvalues=false
android.defaults.buildfeatures.shaders=false
```
