plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jmailen.kotlinter")
    id("kotlinx-atomicfu")
    `maven-publish`
}

kotlin {
    explicitApi()

    js().browser()
    android()
    macosX64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(coroutines("core"))
                api(uuid())
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(coroutines("android"))
            }
        }

        val macosX64Main by getting {
            kotlin.srcDir("src/appleMain/kotlin")

            dependencies {
                implementation(coroutines("core", version = "1.3.9-native-mt-2!!"))
                implementation(stately("isolate-macosx64"))
            }
        }
        val macosX64Test by getting { kotlin.srcDir("src/appleTest/kotlin") }

        all {
            languageSettings.enableLanguageFeature("InlineClasses")
        }
    }
}

android {
    compileSdkVersion(30)

    defaultConfig {
        minSdkVersion(21)
    }

    lintOptions {
        isAbortOnError = true
        isWarningsAsErrors = true
    }

    sourceSets {
        val main by getting {
            manifest.srcFile("src/androidMain/AndroidManifest.xml")
        }
    }
}
