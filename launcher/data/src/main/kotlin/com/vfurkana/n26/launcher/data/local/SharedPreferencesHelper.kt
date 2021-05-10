package com.vfurkana.n26.launcher.data.local

import android.content.SharedPreferences
import com.vfurkana.n26.launcher.data.di.SPTutorialKey
import javax.inject.Inject

class SharedPreferencesHelper @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    @SPTutorialKey private val sharedPrefTutorialShownKey: String
) {
    fun getTutorialShown(): Boolean {
        return sharedPreferences.getBoolean(sharedPrefTutorialShownKey, false)
    }
}

object ReadWriteException : Exception()