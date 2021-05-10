plugins {
    id("com.android.dynamic-feature")
    kotlin("android")
}

android {
    compileSdkVersion(Versions.compileSdk)
    buildToolsVersion(Versions.buildTools)

    defaultConfig {
        minSdkVersion(Versions.minSdk)
        targetSdkVersion(Versions.targetSdk)
    }

    //for dagger
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    sourceSets {
        map { it.java.srcDir("src/${it.name}/kotlin/") }
    }

    //for viewmodels() extension
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    //Common android dependencies
    implementation("androidx.core:core-ktx:${Versions.ktx}")
    implementation("androidx.appcompat:appcompat:${Versions.appCompat}")
}