package com.artlite.sdkchipworkspaceandroid.ui.activities

import android.os.Bundle
import com.artlite.sdkchipworkspaceandroid.R
import com.artlite.sdkchipworkspaceandroid.constants.ChipsConstants
import com.artlite.sdkchipworkspaceandroid.ui.abs.BaseActivity
import com.artlite.skd.chips.ui.views.ChipsHeaderView

/**
 * Main activity class.
 */
class MainActivity : BaseActivity(R.layout.activity_main) {

    private val viewChipsHeader get() = findViewById<ChipsHeaderView>(R.id.view_chips_header)

    /**
     * Method which provide the create functional.
     * @param bundle Bundle? instance.
     */
    override fun onCreated(bundle: Bundle?) {
        viewChipsHeader.configure(ChipsConstants.filter, ChipsConstants.chips)
    }

}