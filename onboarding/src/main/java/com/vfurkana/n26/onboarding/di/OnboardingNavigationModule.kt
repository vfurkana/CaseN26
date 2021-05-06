package com.vfurkana.n26.onboarding.di

import com.vfurkana.n26.bc.MainActivity
import com.vfurkana.n26.navigation.*
import com.vfurkana.n26.onboarding.view.OnboardingActivity
import com.vfurkana.n26.onboarding.view.TestActivity
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class OnboardingNavigationModule {

    @[Provides IntoMap IntentResolverKey(IntentKey.Onboarding::class)]
    fun provideOnboardingIntentResolver(): IntentResolver<*> {
        return OnboardingActivity
    }

    @[Provides IntoMap IntentResolverKey(IntentKey.Test::class)]
    fun provideTestIntentResolver(): IntentResolver<*> {
        return TestActivity
    }
}