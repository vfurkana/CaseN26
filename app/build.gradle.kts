plugins {
    id("base-app-config")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    dynamicFeatures = mutableSetOf(":onboarding:onboarding", ":charts:charts", ":launcher:launcher")

    kapt {
        correctErrorTypes = true
    }
}
android.buildFeatures.viewBinding = true

dependencies {
    //Dependency Injection
    api("com.google.dagger:hilt-android:${Versions.hilt}")
    kapt("com.google.dagger:hilt-compiler:${Versions.hilt}")

    //Dagger issue
    implementation("com.google.dagger:dagger:${Versions.dagger}")
    kapt("com.google.dagger:dagger-compiler:${Versions.dagger}")

    //Google Material Libraries
    implementation("com.google.android.material:material:${Versions.material}")

    //Android activity and fragment extensions
    implementation("androidx.activity:activity-ktx:${Versions.activityKtx}")
    implementation("androidx.fragment:fragment-ktx:${Versions.fragmentKtx}")

    //Http Requests
    implementation("com.squareup.okhttp3:okhttp:${Versions.okhttp}")
    implementation("com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}")
    implementation("com.squareup.retrofit2:retrofit:${Versions.retrofit}")
    implementation("com.squareup.retrofit2:converter-gson:${Versions.retrofit}")

    //For json conversion
    implementation("com.google.code.gson:gson:${Versions.gson}")

    //Play core for dynamic features
    implementation("com.google.android.play:core:${Versions.playCore}")
    implementation("com.google.android.play:core-ktx:${Versions.playCoreKTX}")

}