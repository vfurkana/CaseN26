plugins {
    id("base-lib-config")
    kotlin("kapt")
}

dependencies {

    //Dependency Injection
    implementation("com.google.dagger:dagger:${Versions.dagger}")
    kapt("com.google.dagger:dagger-compiler:${Versions.dagger}")
}