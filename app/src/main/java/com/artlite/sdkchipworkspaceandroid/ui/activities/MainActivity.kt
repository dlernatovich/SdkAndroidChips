package com.artlite.sdkchipworkspaceandroid.ui.activities

import android.os.Bundle
import android.util.Log
import com.artlite.sdkchipworkspaceandroid.R
import com.artlite.sdkchipworkspaceandroid.constants.ChipsConstants
import com.artlite.sdkchipworkspaceandroid.ui.abs.BaseActivity
import com.artlite.skd.chips.impl.models.ChipFilterModel
import com.artlite.skd.chips.impl.models.ChipsModel
import com.artlite.skd.chips.impl.models.getChips
import com.artlite.skd.chips.impl.models.getSelectedChips
import com.artlite.skd.chips.ui.views.ChipsHeaderView
import com.artlite.skd.chips.ui.views.ChipsHeaderViewDelegate

/**
 * Main activity class.
 */
class MainActivity : BaseActivity(R.layout.activity_main), ChipsHeaderViewDelegate {

    private val viewChipsHeader get() = findViewById<ChipsHeaderView>(R.id.view_chips_header)

    /**
     * Method which provide the create functional.
     * @param bundle Bundle? instance.
     */
    override fun onCreated(bundle: Bundle?) {
        this.viewChipsHeader.configure(ChipsConstants.filter, ChipsConstants.chips)
        this.viewChipsHeader.setDelegate(this)
    }

    /**
     * Method which provide the action when chips view was updated.
     * @param filter ChipFilterModel instance.
     * @param chips ChipsModel instance.
     */
    override fun chipsHeaderViewUpdated(filter: ChipFilterModel, chips: ChipsModel) {
        val selected = chips.getSelectedChips()
        Log.e(this::class.java.name, "chipsHeaderViewUpdated -> $selected")
    }

}