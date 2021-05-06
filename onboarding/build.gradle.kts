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
        applicationId = "com.vfurkana.n26.onboarding"
    }
}


dependencies {
    implementation(project(":app"))
    implementation(project(":navigation"))

    //Dependency Injection
    implementation("com.google.dagger:dagger:${Versions.dagger}")
    kapt("com.google.dagger:dagger-compiler:${Versions.dagger}")

    //Android activity and fragment extensions
    implementation("androidx.activity:activity-ktx:${Versions.activityKtx}")
    implementation("androidx.fragment:fragment-ktx:${Versions.fragmentKtx}")
}