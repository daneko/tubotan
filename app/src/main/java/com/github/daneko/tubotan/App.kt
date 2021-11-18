package com.github.daneko.tubotan

import android.app.Application
import androidx.annotation.CallSuper
import com.github.daneko.tubotan.infra.log.CrashlyticsTree
import timber.log.Timber

open class App : Application() {

    @CallSuper
    override fun onCreate() {
        super.onCreate()
        Timber.plant(CrashlyticsTree())
    }
}
