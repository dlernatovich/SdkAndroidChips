package com.artlite.skd.chips.ui.views

import android.content.Context
import android.util.AttributeSet
import com.artlite.skd.chips.ui.abs.AbsView
import com.artlite.skd.ships.R

/**
 * View for the filter button.
 * @constructor
 */
internal class ItemFilterView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AbsView(context, attrs) {

    /** Get layout ID functional. */
    override fun getLayoutId(): Int = R.layout.view_sdk_chips_item_filter

    /** On create view functional. */
    override fun onCreateView() {}

    /** Method which provide the dismissed functional. */
    override fun onDismissed() {}
}