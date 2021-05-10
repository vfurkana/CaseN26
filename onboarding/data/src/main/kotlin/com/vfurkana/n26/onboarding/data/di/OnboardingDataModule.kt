package com.vfurkana.n26.onboarding.data.di

import android.content.SharedPreferences
import com.vfurkana.n26.onboarding.data.local.SharedPreferencesHelper
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier

@Module
class OnboardingDataModule {

    @Provides
    fun provideSharedPreferencesHelper(
        sharedPreferences: SharedPreferences,
        @SPTutorialKey tutorialShownKey: String
    ): SharedPreferencesHelper {
        return SharedPreferencesHelper(sharedPreferences, tutorialShownKey)
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SPTutorialKey