/*
 * Frosted Aurora - Dynamic Frosted Gradient Background SDK for Compose Multiplatform
 * Author: Willian Santos - solobit.com.br
 * License: Creative Commons Attribution-NonCommercial 4.0 International (CC BY-NC 4.0)
 * https://creativecommons.org/licenses/by-nc/4.0/
 */

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

rootProject.name = "frosted-aurora-sdk"
include(":frosted-aurora")
