package com.artlite.skd.chips.facade.abs

import android.content.Context

/**
 * Base lifecycle interface.
 */
interface AbsLifecycle {

    /**
     * Method which provide the create functional.
     * @param context Context instance.
     */
    fun onCreate(context: Context)

    /**
     * Method which provide the destroy functional.
     */
    fun onDestroy()

}