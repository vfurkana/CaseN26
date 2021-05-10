package com.vfurkana.n26.onboarding.di

import android.content.Context
import com.vfurkana.n26.bc.di.OnboardingFeatureDependencies
import com.vfurkana.n26.onboarding.data.di.OnboardingDataModule
import com.vfurkana.n26.onboarding.data.di.SPTutorialKey
import com.vfurkana.n26.onboarding.view.OnboardingActivity
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [OnboardingFeatureDependencies::class],
    modules = [
        OnboardingViewModelsModule::class,
        OnboardingDataDependenciesModule::class,
        OnboardingDataModule::class]
)
interface OnboardingComponent {

    @SPTutorialKey
    fun provideTutorialKey(): String
    fun inject(onboardingActivity: OnboardingActivity)

    @Component.Builder
    interface Builder {
        fun featureDependencies(featureDependencies: OnboardingFeatureDependencies): Builder
        fun build(): OnboardingComponent
    }
}