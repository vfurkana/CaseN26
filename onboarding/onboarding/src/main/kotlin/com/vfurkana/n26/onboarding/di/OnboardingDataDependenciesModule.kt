package com.vfurkana.n26.onboarding.di

import com.vfurkana.n26.bc.di.SharedPrefTutorialShownKey
import com.vfurkana.n26.onboarding.data.di.SPTutorialKey
import dagger.Module
import dagger.Provides


//todo use binds
@Module
class OnboardingDataDependenciesModule {

    @Provides
    @SPTutorialKey
    fun provideTutorialShownKey(@SharedPrefTutorialShownKey key: String): String {
        return key
    }
}