package com.vfurkana.n26.onboarding.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.vfurkana.n26.bc.di.OnboardingFeatureDependencies
import com.vfurkana.n26.bc.utils.gone
import com.vfurkana.n26.bc.utils.visible
import com.vfurkana.n26.onboarding.databinding.ActivityOnboardingBinding
import com.vfurkana.n26.onboarding.di.DaggerOnboardingComponent
import com.vfurkana.n26.onboarding.viewmodel.OnboardingViewModel
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class OnboardingActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: OnboardingViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(OnboardingViewModel::class.java)
    }

    lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        DaggerOnboardingComponent.builder().featureDependencies(
            EntryPointAccessors.fromApplication(
                applicationContext,
                OnboardingFeatureDependencies::class.java
            )
        ).build().inject(this)

        val pagerAdapter =
            OnboardingFragmentPagerAdapter(this).apply { pages = defaultOnboardingPageList; }
        binding.viewpager.adapter = pagerAdapter

        binding.viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (pagerAdapter.pages[position].hasSkip) {
                    binding.btSkip.visible();
                } else {
                    binding.btSkip.gone()
                }
            }
        })
        TabLayoutMediator(binding.tablayout, binding.viewpager) { _, _ -> }.attach()

        viewModel.eventLiveData.observe(this, {
            Log.i("furk", it)
        })
        binding.btSkip.setOnClickListener {
            viewModel.onSkip()
        }
    }
}