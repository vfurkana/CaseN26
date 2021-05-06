plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdkVersion(Versions.compileSdk)
    buildToolsVersion(Versions.buildTools)

    defaultConfig {
        applicationId = "com.vfurkana.n26.bc"
        minSdkVersion(Versions.minSdk)
        targetSdkVersion(Versions.targetSdk)
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    //for hilt
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    //for viewmodels() extension
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            applicationIdSuffix = ".debug"
        }
        getByName("release") {
            isMinifyEnabled = true
        }
    }

    viewBinding {
        android.buildFeatures.viewBinding = true
    }
}

dependencies {
    //Common android dependencies
    implementation("androidx.core:core-ktx:${Versions.ktx}")
    implementation("androidx.appcompat:appcompat:${Versions.appCompat}")
}