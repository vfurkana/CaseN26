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

    //livedata kotlin extensions
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}")

    //ViewFeatures
    implementation("com.google.android.material:material:${Versions.material}")

    //Android activity and fragment extensions
    implementation("androidx.activity:activity-ktx:${Versions.activityKtx}")
    implementation("androidx.fragment:fragment-ktx:${Versions.fragmentKtx}")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlin_coroutines}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlin_coroutines}")

    //LineChart
    implementation("com.github.PhilJay:MPAndroidChart:${Versions.mpAndroidChart}")

    /////Test Dependencies
    testImplementation("junit:junit:${Versions.junit}")
    testImplementation("org.mockito.kotlin:mockito-kotlin:${Versions.mockito}")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.kotlin_coroutines}")
    testImplementation("com.google.truth:truth:${Versions.truth}")
    testImplementation("org.mockito:mockito-inline:${Versions.mockito_inline}")
    testImplementation("androidx.arch.core:core-testing:${Versions.core_testing}")
}