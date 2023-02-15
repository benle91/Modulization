plugins {
    id(AppConfig.PluginId.libraryPlugin)
}
android {
}
dependencies {
    api(project(AppConfig.Modules.NetWork))
    api(project(AppConfig.Modules.Room))

}