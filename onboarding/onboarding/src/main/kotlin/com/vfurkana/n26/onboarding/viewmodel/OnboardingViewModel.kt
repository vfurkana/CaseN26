package com.vfurkana.n26.onboarding.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vfurkana.n26.bc.di.IODispatcher
import com.vfurkana.n26.bc.di.MainDispatcher
import com.vfurkana.n26.onboarding.data.local.SharedPreferencesHelper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class OnboardingViewModel @Inject constructor(
    @MainDispatcher val mainDispatcher: CoroutineDispatcher,
    @IODispatcher val ioDispatcher: CoroutineDispatcher,
    val sharedPreferencesHelper: SharedPreferencesHelper
) : ViewModel() {

    val eventLiveData = MutableLiveData<String>()

    fun onSkip() {
        flow {
            val saveSuccess = sharedPreferencesHelper.saveTutorialShown()
            emit(saveSuccess)
        }.flowOn(ioDispatcher)
            .onStart { eventLiveData.value = "Start" }
            .onEach { eventLiveData.value = "isSuccess:$it" }
            .catch { eventLiveData.value = "catch: $it" }
            .flowOn(mainDispatcher)
            .launchIn(viewModelScope)
    }
}