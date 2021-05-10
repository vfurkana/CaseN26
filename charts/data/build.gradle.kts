plugins {
    id("base-lib-config")
    kotlin("kapt")
}

dependencies {
    implementation(project(":charts:domain"))

    implementation("org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlin_coroutines}")

    // For api
    implementation("com.squareup.retrofit2:retrofit:${Versions.retrofit}")

    //For json conversion
    implementation("com.google.code.gson:gson:${Versions.gson}")

    //Dependency Injection
    implementation("com.google.dagger:dagger:${Versions.dagger}")
    kapt("com.google.dagger:dagger-compiler:${Versions.dagger}")
}