pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
        maven { setUrl("https://maven.neoforged.net/releases") }
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}