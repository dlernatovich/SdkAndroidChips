package com.artlite.sdkchipworkspaceandroid.core

import android.app.Application
import com.artlite.skd.chips.core.SdkChips

/**
 * Current application class.
 */
class CurrentApplication : Application() {

    /**
     * Method which provide the on create functional.
     */
    override fun onCreate() {
        super.onCreate()
        SdkChips.onCreate(this)
    }

    /**
     * Method which provide terminate functionality.
     */
    override fun onTerminate() {
        SdkChips.onDestroy()
        super.onTerminate()
    }

}