package com.artlite.skd.chips.facade.managers

import com.artlite.skd.chips.facade.abs.AbsLifecycle

/**
 * Haptic manager.
 */
internal interface HapticManager: AbsLifecycle {

    /**
     * Method which provide to play haptic.
     */
    fun playHaptic()

}