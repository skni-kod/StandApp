
plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization") version "1.7.10"
    kotlin("native.cocoapods")
    id("com.android.library")
    id("io.kotest.multiplatform") version "5.0.2"
}
version = "1.0"

kotlin {
    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()
   /* jvm {
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }*/
    cocoapods {

        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
        }
        xcodeConfigurationToNativeBuildType["CUSTOM_DEBUG"] = org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.DEBUG
        xcodeConfigurationToNativeBuildType["CUSTOM_RELEASE"] = org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.RELEASE
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                with(Deps.KotlinSerialization) {
                    api(core)
                }
                with(Deps.Ktor) {
                    implementation(core)
                    api(Deps.Ktor.negotiation)
                    api(Deps.Ktor.json)
                }
                with(Deps.Koin) {
                    api(core)
                    api(test)
                }
            }
        }
        val commonTest by getting {
            dependencies {
                with(Deps.Ktor) {
                    implementation(test)
                }
                with(Deps.Koin) {
                    // implementation(junit5)
                }
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
            /*    implementation(kotlin("test")) {
                    exclude("org.jetbrains.kotlin", "kotlin-test-junit")
                }*/

                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))

                    implementation("io.kotest:kotest-property:5.5.1")
                    implementation("io.kotest:kotest-assertions-core:5.5.1")
            }
        }

        val androidMain by getting {
            dependencies {
                with(Deps.Ktor) {
                    implementation(Deps.Ktor.okHttp)
                }
            }
        }
        val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                with(Deps.Ktor) {
                    implementation(Deps.Ktor.darwin)
                }
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    compileSdk = 33
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 28
        targetSdk = 33
    }
/*    testOptions {
        unitTests.all { test ->
            test.useJUnitPlatform()
        }
    }*/
}
