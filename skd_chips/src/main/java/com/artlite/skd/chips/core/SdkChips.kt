package com.artlite.skd.chips.core

import android.content.Context
import com.artlite.skd.chips.facade.abs.AbsLifecycle
import com.artlite.skd.chips.facade.helpers.Base64Helper
import com.artlite.skd.chips.facade.managers.ActivityManager
import com.artlite.skd.chips.facade.managers.ChipsManager
import com.artlite.skd.chips.facade.managers.ContextManager
import com.artlite.skd.chips.facade.managers.HapticManager
import com.artlite.skd.chips.facade.managers.TestingManager
import com.artlite.skd.chips.impl.helpers.Base64HelperImpl
import com.artlite.skd.chips.impl.managers.ActivityManagerImpl
import com.artlite.skd.chips.impl.managers.ContextManagerImpl
import com.artlite.skd.chips.impl.managers.TestingManagerImpl
import com.artlite.skd.chips.impl.managers.ChipsManagerImpl
import com.artlite.skd.chips.impl.managers.HapticManagerImpl

/**
 * Object which provide the chip sdk configuration.
 */
object SdkChips : AbsLifecycle {

    /**
     * Method which provide on create functional.
     * @param context Context instance.
     */
    override fun onCreate(context: Context) {
        Helpers.helpers.forEach { it.onCreate(context) }
        Managers.managers.forEach { it.onCreate(context) }
    }

    /**
     * Method which provide the on destroy functional.
     */
    override fun onDestroy() {
        Helpers.helpers.forEach { it.onDestroy() }
        Managers.managers.forEach { it.onDestroy() }
    }


    /**
     * Helpers for Chips.
     */
    object Helpers {
        internal val base64: Base64Helper get() = Base64HelperImpl
        internal val helpers get() = listOf(base64)
    }

    /**
     * Managers for Chips.
     */
    object Managers {
        internal val activity: ActivityManager get() = ActivityManagerImpl
        internal val context: ContextManager get() = ContextManagerImpl
        internal val test: TestingManager get() = TestingManagerImpl
        val chips: ChipsManager get() = ChipsManagerImpl
        internal val haptic: HapticManager get() = HapticManagerImpl
        internal val managers get() = listOf(activity, context, chips, haptic, test)
    }

}