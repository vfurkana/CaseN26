package com.vfurkana.n26.onboarding.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vfurkana.n26.bc.di.viewmodel.ViewModelFactory
import com.vfurkana.n26.bc.di.viewmodel.ViewModelKey
import com.vfurkana.n26.onboarding.viewmodel.OnboardingViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface OnboardingViewModelsModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(OnboardingViewModel::class)
    fun onboardingViewModel(viewModel: OnboardingViewModel): ViewModel
}