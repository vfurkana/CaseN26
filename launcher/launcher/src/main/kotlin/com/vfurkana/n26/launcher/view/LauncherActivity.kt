package com.vfurkana.n26.launcher.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vfurkana.n26.bc.BuildConfig
import com.vfurkana.n26.bc.di.LauncherFeatureDependencies
import com.vfurkana.n26.bc.navigation.NavigationPoint
import com.vfurkana.n26.bc.utils.StatefulData
import com.vfurkana.n26.launcher.R
import com.vfurkana.n26.launcher.data.local.ReadWriteException
import com.vfurkana.n26.launcher.di.DaggerLauncherComponent
import com.vfurkana.n26.launcher.viewmodel.LauncherViewModel
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class LauncherActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: LauncherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerLauncherComponent.builder().featureDependencies(
            EntryPointAccessors.fromApplication(
                applicationContext,
                LauncherFeatureDependencies::class.java
            )
        ).build().inject(this)
        lifecycle.addObserver(viewModel)

        viewModel.eventLiveData.observe(this, {
            when (it) {
                is StatefulData.Progress -> {
                }
                is StatefulData.Success -> {
                    startActivity(
                        Intent().setClassName(
                            BuildConfig.APPLICATION_ID,
                            it.data.className
                        )
                    )
                }
                is StatefulData.Error -> {
                }
            }
        })
    }
}