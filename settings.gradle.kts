pluginManagement {
    includeBuild("build-logic")
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
include(":core")
include(":core:data-local")
include(":core:domain")
include(":core:data-remote")
include(":core:data")
include(":core:di")
include(":core:ui-components")
include(":core:ui-theme")
include(":core:resources")
include(":core:utils")
include(":core:base")
include(":feature:weapons-list")
include(":feature:teams-and-builds")
include(":core:models")
include(":core:models:common")
