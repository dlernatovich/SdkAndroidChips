package com.artlite.skd.chips.impl.managers

import android.content.Context
import android.view.HapticFeedbackConstants
import com.artlite.skd.chips.facade.managers.HapticManager

internal object HapticManagerImpl: HapticManager {

    /**
     * Method which provide the create functional.
     * @param context Context instance.
     */
    override fun onCreate(context: Context) {}

    /**
     * Method which provide the destroy functional.
     */
    override fun onDestroy() {}

    /**
     * Method which provide to play haptic.
     */
    override fun playHaptic() {
        ActivityManagerImpl.topActivity
            ?.window
            ?.decorView
            ?.rootView
            ?.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
    }

}