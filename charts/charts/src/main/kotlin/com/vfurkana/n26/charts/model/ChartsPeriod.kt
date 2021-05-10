package com.vfurkana.n26.charts.model

import androidx.annotation.StringRes
import com.vfurkana.n26.charts.R
import com.vfurkana.n26.charts.domain.model.ChartsDateRangeDomain

enum class ChartsPeriod(@StringRes val value: Int) {
    ONE_WEEK(R.string.period_one_week),
    ONE_MONTH(R.string.period_one_month),
    ONE_YEAR(R.string.period_one_year),
    THREE_YEAR(R.string.period_three_years);

    fun toDomain(): ChartsDateRangeDomain {
        return ChartsDateRangeDomain.values().firstOrNull { it.name == name } ?: ChartsDateRangeDomain.ONE_MONTH
    }
}