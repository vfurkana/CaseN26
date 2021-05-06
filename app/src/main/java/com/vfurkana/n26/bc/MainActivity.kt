package com.vfurkana.n26.bc

import android.app.Activity
import android.app.AppComponentFactory
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vfurkana.n26.bc.databinding.ActivityMainBinding
import com.vfurkana.n26.navigation.IntentKey
import com.vfurkana.n26.navigation.IntentResolver
import com.vfurkana.n26.navigation.IntentResolverMap
import com.vfurkana.n26.navigation.Navigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject lateinit var navigator: Navigator
    @Inject lateinit var mapppo: IntentResolverMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.mainTv.setOnClickListener {
            startActivity(navigator.createIntent(this, IntentKey.Main("")))
        }
    }

    companion object : IntentResolver<IntentKey.Main> {
        const val EXTRAS_SOME_STRING = "EXTRAS_SOME_STRING"
        override fun getIntent(context: Context, key: IntentKey.Main): Intent {
            return Intent(context, MainActivity::class.java).apply {
                putExtra(EXTRAS_SOME_STRING, key.message)
            }
        }
    }
}