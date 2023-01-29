plugins {
    id(AppConfig.PluginId.libraryPlugin)
}
android {
    namespace = AppConfig.namespace
}
dependencies {
    api(project(AppConfig.Modules.NetWork))
    api(project(AppConfig.Modules.Room))

}