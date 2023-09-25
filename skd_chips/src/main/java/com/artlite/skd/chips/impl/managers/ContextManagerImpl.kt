package com.artlite.skd.chips.impl.managers

import android.content.Context
import com.artlite.skd.chips.facade.managers.ContextManager
import java.lang.ref.WeakReference

/**
 * Implementation of the [ContextManager].
 */
internal object ContextManagerImpl : ContextManager {
    private var contextRef: WeakReference<Context>? = null
    override val context: Context? get() = contextRef?.get()
    override val requiredContext: Context get() = context!!

    /**
     * Method which provide the create functional.
     * @param context Context instance.
     */
    override fun onCreate(context: Context) {
        this.contextRef = WeakReference(context)
    }

    /**
     * Method which provide the destroy functional.
     */
    override fun onDestroy() {
        this.contextRef = null
    }
}