package com.vfurkana.n26.launcher.viewmodel

import androidx.lifecycle.*
import com.vfurkana.n26.bc.di.IODispatcher
import com.vfurkana.n26.bc.di.MainDispatcher
import com.vfurkana.n26.bc.navigation.NavigationPoint
import com.vfurkana.n26.bc.utils.StatefulData
import com.vfurkana.n26.launcher.data.local.SharedPreferencesHelper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class LauncherViewModel @Inject constructor(
    val sharedPreferencesHelper: SharedPreferencesHelper,
    @IODispatcher val ioDispatcher: CoroutineDispatcher,
    @MainDispatcher val mainDispatcher: CoroutineDispatcher
) : ViewModel(),LifecycleObserver {

    val eventLiveData = MutableLiveData<StatefulData<NavigationPoint>>()

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        viewModelScope.launch(ioDispatcher) {
            val isTutorialShow = sharedPreferencesHelper.getTutorialShown()
            withContext(mainDispatcher) {
                if (isTutorialShow) eventLiveData.value =
                    StatefulData.Success(NavigationPoint.Charts)
                else eventLiveData.value = StatefulData.Success(NavigationPoint.Onbarding)
            }
        }
    }
}