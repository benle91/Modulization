plugins {
    id (AppConfig.PluginId.libraryPlugin)
}

android {
}

dependencies {
    api(project(AppConfig.Modules.Data))
    implementation(Dependencies.libNetwork)
}