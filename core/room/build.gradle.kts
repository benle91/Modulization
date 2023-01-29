plugins {
    id (AppConfig.PluginId.libraryPlugin)
}

android {
    namespace = AppConfig.namespace
}

dependencies {
    api(project(AppConfig.Modules.Data))
    implementation(Dependencies.libRoom)
    annotationProcessor(Dependencies.libAnnotation)
    kapt(Dependencies.libKapt)
}