package com.vfurkana.n26.bc.di

import android.content.SharedPreferences
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface OnboardingFeatureDependencies : AppDependecies {
    fun providesSharedPreferences(): SharedPreferences
    @SharedPrefTutorialShownKey fun provideSharedPrefTutorialShownKey(): String
}