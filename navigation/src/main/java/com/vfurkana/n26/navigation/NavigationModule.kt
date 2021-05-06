package com.vfurkana.n26.navigation

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
class NavigationModule {

    @Provides
    @Singleton
    fun provideNavigator(navigationMap: IntentResolverMap): Navigator {
        return Navigator(navigationMap)
    }
}