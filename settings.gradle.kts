pluginManagement {
    repositories {
        google() // Google's Maven repository
        mavenCentral() // Maven Central repository
        gradlePluginPortal() // Gradle Plugin Portal (optional)
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS) // Enforce using settings repositories
    repositories {
        google() // Required for Android and Hilt
        mavenCentral() // Additional libraries
    }
}
rootProject.name = "User Story"
include(":app")
 