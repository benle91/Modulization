plugins {
    id(AppConfig.PluginId.libraryPlugin)
}

android {
    namespace = AppConfig.namespace
}

dependencies {
    implementation(Dependencies.libData)
    implementation(Dependencies.libPreference)
}