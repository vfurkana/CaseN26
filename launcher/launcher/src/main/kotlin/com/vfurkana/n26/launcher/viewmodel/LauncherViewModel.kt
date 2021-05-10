package com.vfurkana.n26.launcher.viewmodel

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import com.vfurkana.n26.launcher.data.local.SharedPreferencesHelper
import javax.inject.Inject


class LauncherViewModel @Inject constructor(
    val sharedPreferencesHelper: SharedPreferencesHelper
) : ViewModel() {


    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        flow {
            val saveSuccess = sharedPreferencesHelper.getTutorialShown()
            emit(saveSuccess)
        }.flowOn(ioDispatcher)
            .onStart { eventLiveData.value = "Start" }
            .onEach { eventLiveData.value = "isSuccess:$it" }
            .catch { eventLiveData.value = "catch: $it" }
            .flowOn(mainDispatcher)
            .launchIn(viewModelScope)
    }
}