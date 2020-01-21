package com.bebound.sample

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.bebound.sdk.BeBound
import com.bebound.sdk.SdkConfig

class App: Application(), SdkConfig {

    override fun onCreate() {
        super.onCreate()
        BeBound.init(this, this, BeBound.LogConfig.ALL)
    }

    override fun smsBinaryEnabled(): Boolean = true

    override fun appUuid(): String = "354af576-01e2-492f-862f-e84438e511d6" // TODO: You need to change it. It is generated in the Be-Bound Console when you create a project.

    override fun appVersion(): Int = 1 // TODO: You need to change it if you have multiple version in the Be-Bound Console.

    override fun httpsEnabled(): Boolean = true

    override fun smsTextEnabled(): Boolean = true

    override fun amqpEnabled(): Boolean = true

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}