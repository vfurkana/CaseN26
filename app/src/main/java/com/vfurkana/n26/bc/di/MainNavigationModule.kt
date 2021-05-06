package com.vfurkana.n26.bc.di

import com.vfurkana.n26.bc.MainActivity
import com.vfurkana.n26.navigation.IntentKey
import com.vfurkana.n26.navigation.IntentResolver
import com.vfurkana.n26.navigation.IntentResolverKey
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(SingletonComponent::class)
class MainNavigationModule {

    @[Provides IntoMap IntentResolverKey(IntentKey.Main::class)]
    fun provideMainIntentResolver(): IntentResolver<*> {
        return MainActivity
    }
}