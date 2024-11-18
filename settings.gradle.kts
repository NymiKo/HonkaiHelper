pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "HonkaiHelper"
include(":app")
include(":feature")
include(":feature:heroes-list")
include(":core-common")
include(":core")
include(":core:data-local")
include(":core:domain")
include(":core:data-remote")
