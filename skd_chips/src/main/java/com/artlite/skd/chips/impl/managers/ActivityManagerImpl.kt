package com.artlite.skd.chips.impl.managers

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import com.artlite.skd.chips.facade.managers.ActivityManager
import java.lang.ref.WeakReference

/**
 * Implementation of the [ActivityManager].
 */
internal object ActivityManagerImpl : ActivityManager {

    /**
     * Instance of the [WeakReference].
     */
    private var topActivityReference: WeakReference<Activity>? = null

    /**
     * Instance of the [Activity].
     */
    override val topActivity: Activity? get() = topActivityReference?.get()

    /**
     * Method which provide on create functional.
     * @param context Context instance.
     */
    override fun onCreate(context: Context) {
        (context as? Application)?.registerActivityLifecycleCallbacks(this)
    }

    /**
     * Method which provide the destroy functional.
     */
    override fun onDestroy() {
        this.topActivityReference = null
    }

    /**
     * Method which provide the action when activity resumed.
     * @param activity Activity instance.
     */
    override fun onActivityResumed(activity: Activity) {
        this.topActivityReference = WeakReference(activity)
    }

    /** Method which provide the action when activity created. */
    override fun onActivityCreated(activity: Activity, bundle: Bundle?) {}

    /** Method which provide the action when activity started. */
    override fun onActivityStarted(activity: Activity) {}

    /** Method which provide the action when activity paused. */
    override fun onActivityPaused(activity: Activity) {}

    /** Method which provide the action when activity stopped. */
    override fun onActivityStopped(activity: Activity) {}

    /** Method which provide the action when activity saved instance state. */
    override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle) {}

    /** Method which provide the action when activity destroyed. */
    override fun onActivityDestroyed(activity: Activity) {}
}