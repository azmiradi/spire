import org.jetbrains.kotlin.konan.properties.Properties

val propertiesFile = rootProject.file("gradle.properties")
val properties = Properties()
properties.load(propertiesFile.reader())

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")
    id("kotlin-parcelize")
    kotlin("kapt")
    id("com.google.firebase.crashlytics")
}

android {
    namespace = "com.bumblebeeai.spire"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.bumblebeeai.spire"
        minSdk = 24
        targetSdk = 34
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
    }

    buildTypes {
        debug {
            isMinifyEnabled = true
            configure<com.google.firebase.crashlytics.buildtools.gradle.CrashlyticsExtension> {
                mappingFileUploadEnabled = false
            }

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    flavorDimensions += "environment"

    productFlavors {
        create("staging") {
            dimension = "environment"
            configure<com.google.firebase.crashlytics.buildtools.gradle.CrashlyticsExtension> {
                mappingFileUploadEnabled = false
            }
            versionName = properties.getProperty("VERSION_NAME_STAGING")
            versionCode = Integer.parseInt(properties.getProperty("VERSION_CODE_STAGING"))
        }

        create("production") {
            dimension = "environment"
            configure<com.google.firebase.crashlytics.buildtools.gradle.CrashlyticsExtension> {
                mappingFileUploadEnabled = true
            }
            versionName = properties.getProperty("VERSION_NAME_PRODUCTION")
            versionCode = Integer.parseInt(properties.getProperty("VERSION_CODE_PRODUCTION"))
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation(project(mapOf("path" to ":android_base")))

    //Test dependencies
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.1.0")
    testImplementation("com.google.truth:truth:1.1.5")
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")

//    //android test dependencies
//    androidTestImplementation("androidx.test.ext:junit:1.1.5")
//    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
//    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
//    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
//    androidTestImplementation("com.google.truth:truth:1.1.5")
//    androidTestImplementation("androidx.test.ext:junit:1.1.5")
//    androidTestImplementation("app.cash.turbine:turbine:1.0.0")
//    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
//    androidTestImplementation("org.mockito.kotlin:mockito-kotlin:5.1.0")
//    androidTestImplementation("app.cash.turbine:turbine:1.0.0")
//    androidTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")

    //debug dependencies
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    //hilt dependency
    implementation("com.google.dagger:hilt-android:2.46")
    kapt("com.google.dagger:hilt-android-compiler:2.46")

    //Firebase
    implementation(platform("com.google.firebase:firebase-bom:32.3.1"))
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-crashlytics-ktx")
}

tasks.register("increaseStagingVersionCode") {
    doLast {
        val versionCode = Integer.parseInt(properties.getProperty("VERSION_CODE_STAGING")) + 1
        val versionName = properties.getProperty("VERSION_NAME_STAGING").split(".").toMutableList()
        val last = Integer.parseInt(versionName[versionName.size - 1])
        versionName[versionName.size - 1] = (last + 1).toString()
        val versionNameStr = versionName.joinToString(".")

        properties.setProperty("VERSION_CODE_STAGING", versionCode.toString())
        properties.setProperty("VERSION_NAME_STAGING", versionNameStr)
        propertiesFile.outputStream().use {
            properties.store(it, "Updated version properties")
        }
    }
}

tasks.register("increaseProductionVersionCode") {
    doLast {
        val versionCode = Integer.parseInt(properties.getProperty("VERSION_CODE_STAGING")) + 1
        val versionName = properties.getProperty("VERSION_NAME_STAGING").split(".").toMutableList()
        val last = Integer.parseInt(versionName[versionName.size - 1])
        versionName[versionName.size - 1] = (last + 1).toString()
        val versionNameStr = versionName.joinToString(".")

        properties.setProperty("VERSION_CODE_STAGING", versionCode.toString())
        properties.setProperty("VERSION_NAME_STAGING", versionNameStr)
        propertiesFile.outputStream().use {
            properties.store(it, "Updated version properties")
        }
    }
}