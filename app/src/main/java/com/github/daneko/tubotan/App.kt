package com.github.daneko.tubotan

import android.app.Application
import com.github.daneko.tubotan.infra.log.CrashlyticsTree
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(CrashlyticsTree())
    }
}
