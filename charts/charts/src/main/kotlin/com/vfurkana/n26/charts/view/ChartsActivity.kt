package com.vfurkana.n26.charts.view

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.android.material.snackbar.Snackbar
import com.vfurkana.n26.bc.di.ChartsFeatureDependencies
import com.vfurkana.n26.bc.utils.StatefulData
import com.vfurkana.n26.charts.R
import com.vfurkana.n26.charts.databinding.ActivityChartsBinding
import com.vfurkana.n26.charts.di.DaggerChartsComponent
import com.vfurkana.n26.charts.domain.model.ChartsDateRangeDomain
import com.vfurkana.n26.charts.domain.model.ChartsTypeDomain
import com.vfurkana.n26.charts.model.Charts
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
        inject()
        setupViews()
        observe()

        viewModel.onDateRange(ChartsDateRangeDomain.ONE_WEEK)
        binding.chipGroup.check(R.id.chip_market_price)
    }

    private fun observe() {
        viewModel.activeDateRange.observe(this, {
            binding.tvDateRange.setText(it.value, false)
        })

        viewModel.activeChartType.observe(this, {
            when (it) {
                ChartsTypeDomain.TRANSACTIONS_PER_SECOND -> {
                    if (binding.chipGroup.checkedChipId != R.id.chip_transactions) {
                        binding.chipGroup.check(R.id.chip_transactions)
                    }
                }
                ChartsTypeDomain.TRADE_VOLUME_USD -> {
                    if (binding.chipGroup.checkedChipId != R.id.chip_trade_volume) {
                        binding.chipGroup.check(R.id.chip_trade_volume)
                    }
                }
                ChartsTypeDomain.MARKET_PRICE_USD -> {
                    if (binding.chipGroup.checkedChipId != R.id.chip_market_price) {
                        binding.chipGroup.check(R.id.chip_market_price)
                    }
                }
            }
            binding.chipGroup.checkedChipId
        })

        viewModel.chartData.observe(this, {
            when (it) {
                is StatefulData.Progress -> {
                    binding.progressBar.show()
                }
                is StatefulData.Success -> {
                    generateAndShowChartData(it.getSuccessData())
                    binding.progressBar.hide()
                }
                is StatefulData.Error -> {
                    binding.progressBar.hide()
                    Snackbar.make(
                        binding.root,
                        when (it.error) {
                            is KotlinNullPointerException -> getString(R.string.unexpected_error)
                            else -> it.error?.message ?: getString(R.string.unexpected_error)
                        }, Snackbar.LENGTH_INDEFINITE
                    ).setAction(R.string.retry) {
                        viewModel.getChart()
                    }.show()
                }
            }
        })

        viewModel.getChart()
    }

    private fun setupViews() {
        val dateRangeItems = ChartsDateRangeDomain.values()
        val adapter =
            ArrayAdapter(this, R.layout.layout_date_range_dropdown, dateRangeItems.map { it.value })
        binding.tvDateRange.setAdapter(adapter)
        binding.tvDateRange.setOnItemClickListener { parent, view, position, id ->
            viewModel.onDateRange(dateRangeItems[position])
        }

        binding.chipGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.chip_market_price -> {
                    viewModel.onChartType(ChartsTypeDomain.MARKET_PRICE_USD)
                }
                R.id.chip_trade_volume -> {
                    viewModel.onChartType(ChartsTypeDomain.TRADE_VOLUME_USD)
                }
                R.id.chip_transactions -> {
                    viewModel.onChartType(ChartsTypeDomain.TRANSACTIONS_PER_SECOND)
                }
            }
        }
    }

    private fun generateAndShowChartData(successData: Charts?) {
        successData?.let { data ->
            val entries = data.values.mapIndexed { index, value ->
                Entry(index.toFloat(), value.value)
            }

            val lineDataSet = LineDataSet(entries, data.name).also { set ->
                set.color = ContextCompat.getColor(this, R.color.purple_500)
                set.highLightColor = ContextCompat.getColor(this, R.color.teal_700)
                set.axisDependency = YAxis.AxisDependency.LEFT
                set.lineWidth = 2f
                set.setDrawCircles(false)
                set.setDrawValues(false)
                set.setDrawCircleHole(false)
            }
            val lineData = LineData(lineDataSet)
            binding.lcContent.data = lineData
            binding.lcContent.invalidate()
        }
    }


    private fun inject() {
        DaggerChartsComponent.builder().featureDependencies(
            EntryPointAccessors.fromApplication(
                applicationContext,
                ChartsFeatureDependencies::class.java
            )
        ).build().inject(this)
    }
}