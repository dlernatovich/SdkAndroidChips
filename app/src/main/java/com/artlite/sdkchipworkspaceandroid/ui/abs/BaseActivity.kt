package com.artlite.sdkchipworkspaceandroid.ui.abs

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

/**
 * Abstract activity class.
 * @property layoutId Int value.
 * @constructor
 */
abstract class BaseActivity(@LayoutRes private val layoutId: Int) : AppCompatActivity() {

    /**
     * Method which provide the create functional.
     * @param bundle Bundle? instance.
     */
    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        setContentView(layoutId)
        this.onCreated(bundle)
    }

    /**
     * Method which provide the create functional.
     * @param bundle Bundle? instance.
     */
    abstract fun onCreated(bundle: Bundle?)

}