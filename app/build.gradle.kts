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
    id("org.jetbrains.kotlin.plugin.serialization")
}

android {
    namespace = "com.bumblebeeai.spire"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.bumblebeeai.spire"
        minSdk = 24
        targetSdk = 34
        testInstrumentationRunner = "com.bumblebeeai.spire.CustomTestRunner"
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
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        release {
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
            versionName = properties.getProperty("VERSION_NAME_STAGING")
            versionCode = Integer.parseInt(properties.getProperty("VERSION_CODE_STAGING"))
            buildConfigField("String", "BASE_URL", "\"https://pqziebouzf.execute-api.eu-west-1.amazonaws.com/api/spire_driver_app/\"")
            buildConfigField("String", "GOOGLE_API_KEY","AIzaSyCA_NPQit11QYtzqkSIpMtwFitNr3LcDyM")
        }

        create("production") {
            dimension = "environment"
            versionName = properties.getProperty("VERSION_NAME_PRODUCTION")
            versionCode = Integer.parseInt(properties.getProperty("VERSION_CODE_PRODUCTION"))
            buildConfigField("String", "BASE_URL", "\"https://pqziebouzf.execute-api.eu-west-1.amazonaws.com/api/spire_driver_app/\"")
            buildConfigField("String", "GOOGLE_API_KEY","\"AIzaSyCA_NPQit11QYtzqkSIpMtwFitNr3LcDyM\"")
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
    implementation(project(mapOf("path" to ":location")))
    implementation("androidx.test.ext:junit-ktx:1.1.5")

    //Test dependencies
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.1.0")
    testImplementation("com.google.truth:truth:1.1.5")
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
    testImplementation("com.google.dagger:hilt-android-testing:2.44")
    kaptTest("com.google.dagger:hilt-android-compiler:2.44")

    //android test dependencies
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    androidTestImplementation("com.google.truth:truth:1.1.5")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("app.cash.turbine:turbine:1.0.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("org.mockito.kotlin:mockito-kotlin:5.1.0")
    androidTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.44")
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.44")


    //debug dependencies
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    //hilt dependency
    implementation("com.google.dagger:hilt-android:2.46")
    kapt("com.google.dagger:hilt-android-compiler:2.46")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")

    //Firebase
    implementation(platform("com.google.firebase:firebase-bom:32.3.1"))
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-crashlytics-ktx")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.5")

    implementation("com.google.dagger:hilt-android:2.46")
    kapt("com.google.dagger:hilt-android-compiler:2.46")
    implementation("androidx.biometric:biometric:1.0.1")

    implementation ("androidx.compose.material:material-icons-extended:1.5.4")
    implementation ("com.github.TuleSimon:xMaterialccp:1.27")
    //Navigation
    implementation("androidx.navigation:navigation-compose:2.7.5")
    implementation ("androidx.compose.material:material:1.5.4")
    implementation ("com.google.accompanist:accompanist-systemuicontroller:0.27.0")

    //pagination
    implementation("androidx.paging:paging-runtime-ktx:3.2.1")
    implementation("androidx.paging:paging-compose:3.3.0-alpha02")
    implementation("com.akexorcist:google-direction-library:1.2.1")
    implementation("com.google.android.gms:play-services-location:21.0.1")
    implementation("com.google.maps.android:maps-compose:2.9.1")

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