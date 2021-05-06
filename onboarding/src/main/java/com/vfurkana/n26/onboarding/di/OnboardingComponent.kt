package com.vfurkana.n26.onboarding.di

import android.content.Context
import com.vfurkana.n26.bc.di.OnboardingModuleDependencies
import com.vfurkana.n26.onboarding.view.OnboardingActivity
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [OnboardingModuleDependencies::class],
    modules = [OnboardingNavigationModule::class]
)
interface OnboardingComponent {

    fun inject(activity: OnboardingActivity)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(onboardingModuleDependencies: OnboardingModuleDependencies): Builder
        fun build(): OnboardingComponent
    }
}