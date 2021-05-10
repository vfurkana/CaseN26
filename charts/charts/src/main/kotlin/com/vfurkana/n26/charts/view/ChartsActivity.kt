package com.vfurkana.n26.charts.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.vfurkana.n26.bc.di.ChartsFeatureDependencies
import com.vfurkana.n26.charts.databinding.ActivityChartsBinding
import com.vfurkana.n26.charts.di.DaggerChartsComponent
import com.vfurkana.n26.charts.viewmodel.ChartsViewModel
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class ChartsActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: ChartsViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(ChartsViewModel::class.java)
    }
    lateinit var binding: ActivityChartsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChartsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        EntryPointAccessors.fromApplication(
            applicationContext,
            ChartsFeatureDependencies::class.java
        ).provideRetrofit().baseUrl()
        DaggerChartsComponent.builder().featureDependencies(
            EntryPointAccessors.fromApplication(
                applicationContext,
                ChartsFeatureDependencies::class.java
            )
        ).build().inject(this)

        viewModel.eventLiveData.observe(this, {
            binding.tvCharts.text = it
        })
        binding.tvCharts.setOnClickListener {
            viewModel.getChart()
        }
    }
}