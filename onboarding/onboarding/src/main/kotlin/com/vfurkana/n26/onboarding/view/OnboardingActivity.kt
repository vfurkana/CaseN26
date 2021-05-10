package com.vfurkana.n26.onboarding.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.play.core.splitcompat.SplitCompat
import com.vfurkana.n26.bc.BuildConfig
import com.vfurkana.n26.bc.di.OnboardingFeatureDependencies
import com.vfurkana.n26.bc.navigation.NavigationPoint
import com.vfurkana.n26.bc.utils.StatefulData
import com.vfurkana.n26.bc.utils.gone
import com.vfurkana.n26.bc.utils.visible
import com.vfurkana.n26.launcher.R
import com.vfurkana.n26.launcher.databinding.ActivityOnboardingBinding
import com.vfurkana.n26.onboarding.data.local.ReadWriteException
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
        binding.progressBar.hide()

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
            when (it) {
                is StatefulData.Progress -> {
                    binding.progressBar.show()
                }
                is StatefulData.Success -> {
                    if (it.getSuccessData() == NavigationPoint.Charts) {
                        // TODO : No time left for proper navigation. Replace this with Android Auto Service based navigation implementation.
                        startActivity(
                            Intent().setClassName(
                                BuildConfig.APPLICATION_ID,
                                it.data.className
                            )
                        )
                    }
                    binding.progressBar.hide()
                }
                is StatefulData.Error -> {
                    binding.progressBar.hide()
                    Snackbar.make(
                        binding.root,
                        when (it.error) {
                            is ReadWriteException -> getString(R.string.read_write_error)
                            else -> it.error?.message ?: "Error"
                        }, Snackbar.LENGTH_INDEFINITE
                    )
                }
            }
        })
        binding.btSkip.setOnClickListener {
            viewModel.onSkip()
        }
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        SplitCompat.install(this)
    }
}