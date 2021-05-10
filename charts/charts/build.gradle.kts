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
        applicationId = "com.vfurkana.n26.charts"
    }
}
android.buildFeatures.viewBinding = true

dependencies {
    implementation(project(":app"))
    implementation(project(":charts:domain"))
    implementation(project(":charts:data"))

    //Dependency Injection
    implementation("com.google.dagger:dagger:${Versions.dagger}")
    kapt("com.google.dagger:dagger-compiler:${Versions.dagger}")

    // For api
    implementation("com.squareup.retrofit2:retrofit:${Versions.retrofit}")

    //ViewFeatures
    implementation("com.google.android.material:material:${Versions.material}")

    //Android activity and fragment extensions
    implementation("androidx.activity:activity-ktx:${Versions.activityKtx}")
    implementation("androidx.fragment:fragment-ktx:${Versions.fragmentKtx}")
}