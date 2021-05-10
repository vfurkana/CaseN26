package com.vfurkana.n26.onboarding.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vfurkana.n26.bc.di.IODispatcher
import com.vfurkana.n26.bc.di.MainDispatcher
import com.vfurkana.n26.onboarding.data.local.SharedPreferencesHelper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OnboardingViewModel @Inject constructor(
    @MainDispatcher val mainDispatcher: CoroutineDispatcher,
    @IODispatcher val ioDispatcher: CoroutineDispatcher,
    val sharedPreferencesHelper: SharedPreferencesHelper
) : ViewModel() {

    val eventLiveData = MutableLiveData<String>()

    fun onSkip() {
        viewModelScope.launch(ioDispatcher) {
            sharedPreferencesHelper.saveTutorialShown()
            withContext(mainDispatcher) {
                val readBool = sharedPreferencesHelper.getTutorialShown()
                eventLiveData.value = "Test boolean: $readBool"
            }
        }
    }


}