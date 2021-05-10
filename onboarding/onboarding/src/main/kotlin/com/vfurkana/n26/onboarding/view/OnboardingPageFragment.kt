package com.vfurkana.n26.onboarding.view

import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vfurkana.n26.bc.utils.gone
import com.vfurkana.n26.bc.utils.visible
import com.vfurkana.n26.onboarding.databinding.FragmentOnboardingPageBinding

class OnboardingPageFragment(val onboardingPage: OnboardingPage, val isLast: Boolean = false) :
    Fragment() {

    lateinit var binding: FragmentOnboardingPageBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnboardingPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivIcon.setImageResource(onboardingPage.imageRes)

        binding.tvTitle.text = getString(onboardingPage.descriptionRes)
    }

}