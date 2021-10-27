package com.github.daneko.tubotan.infra.log

import android.annotation.SuppressLint
import android.util.Log
import com.github.daneko.tubotan.BuildConfig
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import timber.log.Timber

class CrashlyticsTree : Timber.Tree() {
    @SuppressLint("LogNotTimber")
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority < Log.WARN && !BuildConfig.DEBUG) {
            return
        }
        if (priority >= Log.WARN) {
            if (t != null) {
                Firebase.crashlytics.recordException(t)
            } else {
                Firebase.crashlytics.log("${tag ?: "unknown tag"}: $message")
            }
        }
        when (priority) {
            Log.VERBOSE -> {
                Log.v(tag, message, t)
            }
            Log.DEBUG -> {
                Log.d(tag, message, t)
            }
            Log.INFO -> {
                Log.i(tag, message, t)
            }
            Log.WARN -> {
                Log.w(tag, message, t)
            }
            Log.ERROR -> {
                Log.e(tag, message, t)
            }
            Log.ASSERT -> {
                Log.wtf(tag, message, t)
            }
            else -> {
                Log.wtf(tag ?: "unknown", message, t)
            }
        }
    }
}
