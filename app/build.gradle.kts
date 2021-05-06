plugins {
    id("base-app-config")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    dynamicFeatures = mutableSetOf(":onboarding")
    kapt {
        correctErrorTypes = true
    }
    hilt {
        enableExperimentalClasspathAggregation = true
    }
    lintOptions.isCheckReleaseBuilds = false
}

dependencies {
    implementation(project(":navigation"))

    //Dependency Injection
    api("com.google.dagger:hilt-android:${Versions.hilt}")
    kapt("com.google.dagger:hilt-compiler:${Versions.hilt}")

    //Google Material Libraries
    implementation("com.google.android.material:material:${Versions.material}")

    //Android activity and fragment extensions
    implementation ("androidx.activity:activity-ktx:${Versions.activityKtx}")
    implementation ("androidx.fragment:fragment-ktx:${Versions.fragmentKtx}")
}