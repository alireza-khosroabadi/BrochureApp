pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "BrochureApp"
include(":app")
include(":data")
include(":core:ui")
include(":domain")
include(":feature-brochure")
include(":core:network")
include(":core:designSystem")
include(":core:model")
include(":core:database")
include(":feature-brochureDetail")
