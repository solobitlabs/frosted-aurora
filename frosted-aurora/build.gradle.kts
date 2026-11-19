/*
 * Frosted Aurora - Dynamic Frosted Gradient Background SDK for Compose Multiplatform
 * Author: Willian Santos - solobit.com.br
 * License: Creative Commons Attribution-NonCommercial 4.0 International (CC BY-NC 4.0)
 * https://creativecommons.org/licenses/by-nc/4.0/
 */

import com.vanniktech.maven.publish.SonatypeHost
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
    id("com.vanniktech.maven.publish")
}

group = "br.com.solobit"
version = "1.0.1"

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

mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL, automaticRelease = true)
    signAllPublications()

    coordinates(group.toString(), "frosted-aurora", version.toString())

    pom {
        name.set("Frosted Aurora")
        description.set("Dynamic frosted gradient background SDK for Compose Multiplatform.")
        inceptionYear.set("2026")
        url.set("https://github.com/solobitlabs/frosted-aurora")

        licenses {
            license {
                name.set("Creative Commons Attribution-NonCommercial 4.0 International (CC BY-NC 4.0)")
                url.set("https://creativecommons.org/licenses/by-nc/4.0/")
                distribution.set("https://creativecommons.org/licenses/by-nc/4.0/")
            }
        }

        developers {
            developer {
                id.set("willjsan")
                name.set("Willian Santos")
                url.set("https://solobit.com.br")
            }
        }

        scm {
            url.set("https://github.com/solobitlabs/frosted-aurora")
            connection.set("scm:git:git://github.com/solobitlabs/frosted-aurora.git")
            developerConnection.set("scm:git:ssh://git@github.com/solobitlabs/frosted-aurora.git")
        }
    }
}
