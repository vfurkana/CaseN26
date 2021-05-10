package com.vfurkana.n26.onboarding.view

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.vfurkana.n26.onboarding.R

data class OnboardingPage(
    val order: Int = 0,
    @DrawableRes val imageRes: Int,
    @StringRes val descriptionRes: Int,
    val hasSkip: Boolean = true
)

val defaultOnboardingPageList = listOf(
    OnboardingPage(0, R.drawable.icon_1, R.string.onboarding_first, false),
    OnboardingPage(0, R.drawable.icon_2, R.string.onboarding_second,false),
    OnboardingPage(0, R.drawable.icon_3, R.string.onboarding_third),
    OnboardingPage(0, R.drawable.icon_4, R.string.onboarding_fourth)
)