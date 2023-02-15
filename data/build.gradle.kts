plugins {
    id(AppConfig.PluginId.libraryPlugin)
}

android {
}

dependencies {
    implementation(Dependencies.libData)
    implementation(Dependencies.libPreference)
}