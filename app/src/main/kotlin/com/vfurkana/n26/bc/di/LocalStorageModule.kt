package com.vfurkana.n26.bc.di

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object LocalStorageModule {
    const val SHARED_PREF_NAME = "CASEING_SHARED_PREFERENCES"
    const val SHARED_PREF_TUTORIAL_SHOWN_KEY = "SHARED_PREF_TUTORIAL_SHOWN_KEY"

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
    }

    @Provides
    @SharedPrefTutorialShownKey
    fun sharedPreferencesTutorialShownKey(): String {
        return SHARED_PREF_TUTORIAL_SHOWN_KEY
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SharedPrefTutorialShownKey