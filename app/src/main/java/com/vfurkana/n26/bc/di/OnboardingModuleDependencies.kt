package com.vfurkana.n26.bc.di

import android.content.SharedPreferences
import com.vfurkana.n26.navigation.Navigator
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@EntryPoint
@InstallIn(SingletonComponent::class)
interface OnboardingModuleDependencies {
    fun providesSharedPreferences(): SharedPreferences
}