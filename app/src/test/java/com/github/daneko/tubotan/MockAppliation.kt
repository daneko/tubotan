package com.github.daneko.tubotan

import android.app.Application
import android.util.Log
import timber.log.Timber

class MockApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        println("hogeee")
        if (Timber.treeCount == 0) {
            Timber.plant(TestTree())
        }
    }

    class TestTree : Timber.Tree() {
        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
            val p = when (priority) {
                Log.VERBOSE -> "VERBOSE"
                Log.DEBUG -> "DEBUG"
                Log.INFO -> "INFO"
                Log.WARN -> "WARN"
                Log.ERROR -> "ERROR"
                Log.ASSERT -> "ASSERT"
                else -> "UNKNOWN"
            }
            println("[$tag:$p] : $message")
            t?.printStackTrace()
        }
    }
}
