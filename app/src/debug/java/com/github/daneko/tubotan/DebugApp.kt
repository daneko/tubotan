package com.github.daneko.tubotan

class DebugApp : App() {

    override fun onCreate() {
        super.onCreate()
        DebugMenuActivity.showDebugMenuOnNotification(this)
        DebugMenuActivity.setDynamicShortcut(this)
    }
}
