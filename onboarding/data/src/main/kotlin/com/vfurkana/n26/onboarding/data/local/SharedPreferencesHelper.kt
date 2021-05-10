package com.vfurkana.n26.onboarding.data.local

import android.content.SharedPreferences
import com.vfurkana.n26.onboarding.data.di.SPTutorialKey
import javax.inject.Inject

class SharedPreferencesHelper @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    @SPTutorialKey private val sharedPrefTutorialShownKey: String
) {
    fun saveTutorialShown() {
        if (!sharedPreferences.edit().putBoolean(sharedPrefTutorialShownKey, true)
                .commit()
        ) throw ReadWriteException
    }

    fun getTutorialShown(): Boolean {
        return sharedPreferences.getBoolean(sharedPrefTutorialShownKey, false)
    }
}

object ReadWriteException : Exception()