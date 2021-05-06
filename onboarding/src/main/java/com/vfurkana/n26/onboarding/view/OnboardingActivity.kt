package com.vfurkana.n26.onboarding.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vfurkana.n26.bc.di.OnboardingModuleDependencies
import com.vfurkana.n26.navigation.IntentKey
import com.vfurkana.n26.navigation.IntentResolver
import com.vfurkana.n26.navigation.Navigator
import com.vfurkana.n26.onboarding.databinding.ActivityOnboardingBinding
import com.vfurkana.n26.onboarding.di.DaggerOnboardingComponent
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject


class OnboardingActivity : AppCompatActivity() {

//    private val viewmodel: OnboardingViewModel by viewModels()
    @Inject lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        DaggerOnboardingComponent
            .builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    this,
                    OnboardingModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)

        binding.tv.setOnClickListener {
            startActivity(navigator.createIntent(this, IntentKey.Test("some message")))
        }
    }


    companion object : IntentResolver<IntentKey.Onboarding> {
        const val EXTRAS_SOME_STRING = "EXTRAS_SOME_STRING"
        override fun getIntent(context: Context, key: IntentKey.Onboarding): Intent {
            return Intent(context, OnboardingActivity::class.java).apply {
                putExtra(EXTRAS_SOME_STRING, key.message)
            }
        }
    }
}