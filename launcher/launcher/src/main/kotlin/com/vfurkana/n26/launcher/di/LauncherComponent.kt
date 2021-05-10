package com.vfurkana.n26.launcher.di

import com.vfurkana.n26.bc.di.LauncherFeatureDependencies
import com.vfurkana.n26.launcher.data.di.SPTutorialKey
import com.vfurkana.n26.launcher.view.LauncherActivity
import dagger.Component

@Component(
    dependencies = [LauncherFeatureDependencies::class],
    modules = [
        LauncherViewModelsModule::class,
        LauncherDataDependenciesModule::class
    ]
)
interface LauncherComponent {

    @SPTutorialKey
    fun provideTutorialKey(): String
    fun inject(launcherActivity: LauncherActivity)

    @Component.Builder
    interface Builder {
        fun featureDependencies(featureDependencies: LauncherFeatureDependencies): Builder
        fun build(): LauncherComponent
    }
}