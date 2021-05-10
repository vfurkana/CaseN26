package com.vfurkana.n26.bc.utils

import android.content.Context
import android.view.View
import android.widget.Toast


sealed class StatefulData<out T> {
    class Success<T>(val data: T) : StatefulData<T>()
    class Error(val error: Throwable? = null) : StatefulData<Nothing>()
    object Progress : StatefulData<Nothing>()

    fun getSuccessData(): T? {
        return (this as? Success)?.data
    }

    fun isLoading(): Boolean {
        return this is Progress
    }
}