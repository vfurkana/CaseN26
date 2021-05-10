package com.vfurkana.n26.bc.navigation

sealed class NavigationPoint(val className: String) {
    object Charts : NavigationPoint("com.vfurkana.n26.charts.view.ChartsActivity")
    object Onbarding : NavigationPoint("com.vfurkana.n26.onboarding.view.OnboardingActivity")
}