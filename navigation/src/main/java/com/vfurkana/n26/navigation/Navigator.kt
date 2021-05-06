package com.vfurkana.n26.navigation

import android.content.Context
import android.content.Intent
import dagger.MapKey
import javax.inject.Provider
import kotlin.reflect.KClass

sealed class IntentKey {
    class Onboarding(val message: String) : IntentKey()
    class Main(val message: String) : IntentKey()
    class Test(val message: String) : IntentKey()
}

interface IntentResolver<in T : IntentKey> {
    fun getIntent(context: Context, key: T): Intent
}

@MapKey
annotation class IntentResolverKey(val value: KClass<out IntentKey>)

typealias IntentResolverMap = @JvmSuppressWildcards Map<Class<out IntentKey>, Provider<IntentResolver<*>>>

open class Navigator(private val intentResolvers: IntentResolverMap) {
    open fun createIntent(context: Context, key: IntentKey): Intent {
        val resolver = intentResolvers[key::class.java]?.get() as IntentResolver<IntentKey>?
        return resolver?.getIntent(context, key)
            ?: throw IllegalStateException("Can not resolve intent key $key")
    }
}