package com.artlite.skd.chips.facade.managers

import android.content.Context
import com.artlite.skd.chips.facade.abs.AbsLifecycle

/**
 * Context manager interface.
 * @property context Context? instance.
 * @property requiredContext Context instance.
 */
internal interface ContextManager: AbsLifecycle {
    val context: Context?
    val requiredContext: Context
}