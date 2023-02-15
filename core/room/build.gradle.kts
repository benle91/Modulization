plugins {
    id (AppConfig.PluginId.libraryPlugin)
}

android {
}

dependencies {
    api(project(AppConfig.Modules.Data))
    implementation(Dependencies.libRoom)
    annotationProcessor(Dependencies.libAnnotation)
    kapt(Dependencies.libKapt)
}