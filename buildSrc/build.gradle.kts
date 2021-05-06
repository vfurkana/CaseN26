import org.gradle.kotlin.dsl.`kotlin-dsl`

plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.32")
    }
}


repositories {
    mavenCentral()
    google()
}

dependencies {
    implementation("com.android.tools.build:gradle:4.2.0")
    implementation("com.android.tools.build:gradle-api:4.2.0")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.32")
}