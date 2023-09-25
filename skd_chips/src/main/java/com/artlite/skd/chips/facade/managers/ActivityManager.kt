package com.artlite.skd.chips.facade.managers

import android.app.Activity
import android.app.Application
import com.artlite.skd.chips.facade.abs.AbsLifecycle

/**
 * Activity manager interface.
 * @property topActivity Activity? instance.
 */
internal interface ActivityManager : Application.ActivityLifecycleCallbacks, AbsLifecycle {
    /** Instance of the [Activity]. */
    val topActivity: Activity?
}