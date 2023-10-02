package com.artlite.skd.chips.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import com.artlite.skd.chips.core.SdkChips
import com.artlite.skd.chips.facade.models.getTextString
import com.artlite.skd.chips.impl.models.ChipFilterModel
import com.artlite.skd.chips.impl.models.ChipsModel
import com.artlite.skd.chips.ui.abs.AbsActivity
import com.artlite.skd.chips.ui.views.ItemSectionDetailsView
import com.artlite.skd.ships.R

/**
 * Filter activity class.
 */
internal class FilterActivity: AbsActivity(R.layout.activity_sdk_chips_filter) {
    companion object;

    /**
     * Instance of the [ChipsModel].
     */
    private lateinit var chips: ChipsModel

    /** Instance of the [View]. */
    private val vAction by lazy { findViewById<View>(R.id.button_action) }
    /** Instance of the [LinearLayout]. */
    private val vItems by lazy { findViewById<LinearLayout>(R.id.view_items) }

    /**
     * Method which provide the activity created functional.
     * @param bundle Bundle? instance.
     */
    override fun onActivityCreated(bundle: Bundle?) = when(val it = Container.filter) {
        null -> Unit
        else -> when(val it1 = SdkChips.Managers.chips.get(it)) {
            null -> Unit
            else -> onInitInterface(it1)
        }
    }

    /**
     * Method which provide the action when activity destroyed.
     */
    override fun onActivityDestroyed() {}

    /**
     * Method which provide to init of the interface.
     * @param it ChipsModel instance.
     */
    private fun onInitInterface(it: ChipsModel) {
        this.vAction.setOnClickListener(onCloseClicked)
        this.chips = it
        this.onApplyInterface(it)
    }

    /**
     * Method which provide the apply interface.
     * @param it ChipsModel instance.
     */
    private fun onApplyInterface(it: ChipsModel) = when(vItems.childCount) {
        0 -> it.sections.forEach { item ->
            val itemView = ItemSectionDetailsView(this)
            itemView.configure(Container.filter!!, item)
            vItems.addView(itemView)
        }
        else -> {

        }
    }

    /**
     * Close clicked functional.
     */
    private val onCloseClicked = OnClickListener {
        playHaptic()
        finish()
    }

}

/**
 * Method which provide to show the filter activity.
 * @receiver FilterActivity receiver.
 * @param it ChipFilterModel instance.
 */
internal fun FilterActivity.Companion.show(it: ChipFilterModel) {
    val topActivity = SdkChips.Managers.activity.topActivity ?: return
    val intent = Intent(topActivity, FilterActivity::class.java)
    Container.filter = it
    topActivity.startActivity(intent)
}

/**
 * Container for the [FilterActivity].
 */
private object Container {
    var filter: ChipFilterModel? = null
}