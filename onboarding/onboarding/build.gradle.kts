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
    buildTypes {
        getByName("debug") {
            applicationIdSuffix = ".debug"
        }
    }
}

android.buildFeatures.viewBinding = true


dependencies {
    implementation(project(":app"))
    implementation(project(":onboarding:data"))

    //Dependency Injection
    implementation("com.google.dagger:dagger:${Versions.dagger}")
    kapt("com.google.dagger:dagger-compiler:${Versions.dagger}")

    //ViewFeatures
    implementation("androidx.viewpager2:viewpager2:${Versions.viewpager2}")
    implementation("com.google.android.material:material:${Versions.material}")

    //Android activity and fragment extensions
    implementation("androidx.activity:activity-ktx:${Versions.activityKtx}")
    implementation("androidx.fragment:fragment-ktx:${Versions.fragmentKtx}")
}