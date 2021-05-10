plugins {
    id("base-feature-config")
    kotlin("android")
    kotlin("kapt")
}
android {
    lintOptions.isCheckReleaseBuilds = false

    kapt {
        correctErrorTypes = true
    }

    defaultConfig {
        applicationId = "com.vfurkana.n26.launcher"
    }
    buildTypes {
        getByName("debug") {
            applicationIdSuffix = ".debug"
        }
    }
}

android.buildFeatures.viewBinding = true


dependencies {
    implementation(project(":app"))
    implementation(project(":launcher:data"))

    //Dependency Injection
    implementation("com.google.dagger:dagger:${Versions.dagger}")
    kapt("com.google.dagger:dagger-compiler:${Versions.dagger}")

    //Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlin_coroutines}")

    //ViewmodelLifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}")

}