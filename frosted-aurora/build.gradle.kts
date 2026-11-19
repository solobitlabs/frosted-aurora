/*
 * Frosted Aurora - Dynamic Frosted Gradient Background SDK for Compose Multiplatform
 * Author: Willian Santos - solobit.com.br
 * License: Creative Commons Attribution-NonCommercial 4.0 International (CC BY-NC 4.0)
 * https://creativecommons.org/licenses/by-nc/4.0/
 */

import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
    id("maven-publish")
}

group = "br.com.solobit"
version = "1.0.0"

kotlin {
    androidTarget {
        publishLibraryVariants("release")
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    jvm("desktop") {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.ui)
            implementation(compose.animation)
        }
    }
}

android {
    namespace = "br.com.solobit.frostedaurora"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

publishing {
    publications.withType<MavenPublication> {
        pom {
            name.set("Frosted Aurora")
            description.set("Dynamic frosted gradient background SDK for Compose Multiplatform.")
            url.set("https://solobit.com.br")
            licenses {
                license {
                    name.set("Creative Commons Attribution-NonCommercial 4.0 International (CC BY-NC 4.0)")
                    url.set("https://creativecommons.org/licenses/by-nc/4.0/")
                }
            }
            developers {
                developer {
                    id.set("willjsan")
                    name.set("Willian Santos")
                    url.set("https://solobit.com.br")
                }
            }
        }
    }

    repositories {
        maven {
            name = "GitHubPackages"
            val repo = System.getenv("GITHUB_REPOSITORY") ?: "solobitlabs/frosted-aurora"
            url = uri("https://maven.pkg.github.com/$repo")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("GITHUB_ACTOR")
                password = project.findProperty("gpr.key") as String? ?: System.getenv("GITHUB_TOKEN")
            }
        }
    }
}
