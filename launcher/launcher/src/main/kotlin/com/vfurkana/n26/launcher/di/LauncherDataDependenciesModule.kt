package com.vfurkana.n26.launcher.di

import com.vfurkana.n26.bc.di.SharedPrefTutorialShownKey
import com.vfurkana.n26.launcher.data.di.SPTutorialKey
import dagger.Module
import dagger.Provides


//todo use binds
@Module
class LauncherDataDependenciesModule {

    @Provides
    @SPTutorialKey
    fun provideTutorialShownKey(@SharedPrefTutorialShownKey key: String): String {
        return key
    }
}