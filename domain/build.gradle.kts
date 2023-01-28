plugins {
    id(AppConfig.PluginId.libraryPlugin)
}
android {
    namespace = AppConfig.namespace
}
dependencies {
    api(project(":core:network"))
}