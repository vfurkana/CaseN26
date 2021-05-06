package com.vfurkana.n26.onboarding.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vfurkana.n26.navigation.IntentKey
import com.vfurkana.n26.navigation.IntentResolver
import com.vfurkana.n26.onboarding.R

class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
    }

    companion object : IntentResolver<IntentKey.Test> {
        const val EXTRAS_SOME_STRING = "EXTRAS_SOME_STRING"
        override fun getIntent(context: Context, key: IntentKey.Test): Intent {
            return Intent(context, TestActivity::class.java).apply {
                putExtra(EXTRAS_SOME_STRING, key.message)
            }
        }

    }
}