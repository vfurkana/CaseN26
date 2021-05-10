package com.vfurkana.n26.launcher.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vfurkana.n26.bc.di.viewmodel.ViewModelFactory
import com.vfurkana.n26.bc.di.viewmodel.ViewModelKey
import com.vfurkana.n26.launcher.viewmodel.LauncherViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface LauncherViewModelsModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(LauncherViewModel::class)
    fun onboardingViewModel(viewModel: LauncherViewModel): ViewModel
}