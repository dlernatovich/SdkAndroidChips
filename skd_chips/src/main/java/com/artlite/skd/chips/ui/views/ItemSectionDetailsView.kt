package com.artlite.skd.chips.ui.views

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.res.ResourcesCompat
import com.artlite.skd.chips.core.SdkChips
import com.artlite.skd.chips.facade.models.Configurable
import com.artlite.skd.chips.facade.models.getTextString
import com.artlite.skd.chips.facade.models.switchExpanded
import com.artlite.skd.chips.impl.managers.ChipsManagerImpl
import com.artlite.skd.chips.impl.models.ChipFilterModel
import com.artlite.skd.chips.impl.models.ChipModel
import com.artlite.skd.chips.impl.models.ChipSectionModel
import com.artlite.skd.chips.impl.models.getSelectedCount
import com.artlite.skd.chips.ui.abs.AbsView
import com.artlite.skd.ships.R
import com.google.android.flexbox.FlexboxLayout
import java.lang.ref.WeakReference

/**
 * View for the items section details view.
 * @constructor
 */
internal class ItemSectionDetailsView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AbsView(context, attrs),
    Configurable<ChipFilterModel, ChipSectionModel>,
    OnClickListener,
    ItemChipViewDelegate {
    // Views.
    private val vHeader by lazy { find<View>(R.id.view_header) }
    private val lHeader by lazy { find<AppCompatTextView>(R.id.label_header) }
    private val lCount by lazy { find<AppCompatTextView>(R.id.label_count) }
    private val bDelete by lazy { find<View>(R.id.image_delete) }
    private val iArrow by lazy { find<AppCompatImageView>(R.id.image_arrow) }
    private val vExpanded by lazy { find<View>(R.id.view_expanded) }
    private val vSubItems by lazy { find<FlexboxLayout>(R.id.sdk_chips_flexbox_sub_view) }
    // Filter.
    private var filterRef: WeakReference<ChipFilterModel>? = null
    private val filter get() = filterRef?.get()!!
    // Section.
    private var sectionRef: WeakReference<ChipSectionModel>? = null
    private val section get() = sectionRef?.get()!!

    /** Get layout ID functional. */
    override fun getLayoutId(): Int = R.layout.view_sdk_chips_item_section_details

    /** On create view functional. */
    override fun onCreateView() {}

    /** Method which provide the dismissed functional. */
    override fun onDismissed() {}

    /**
     * Method which provide the configure functional.
     * @param it T configure file.
     * @param args Array<out K> params.
     */
    override fun configure(it: ChipFilterModel, vararg args: ChipSectionModel) {
        this.filterRef = WeakReference(it)
        this.sectionRef = WeakReference(args.first())
        this.onApplyInterface(section)
    }

    /**
     * Method which provide to apply interface.
     * @param it ChipSectionModel instance.
     */
    private fun onApplyInterface(it: ChipSectionModel) {
        val selectedCount = it.getSelectedCount()
        this.vHeader.setOnClickListener(this)
        this.lHeader.text = it.getTextString()
        this.iArrow.setImageDrawable(getArrow(it))
        if (it.isExpanded) {
            this.vExpanded.visibility = VISIBLE
        } else {
            this.vExpanded.visibility = GONE
        }
        if (vSubItems.childCount <= 0) {
            it.chips.forEach { _ ->
                val chipView = ItemChipView(context)
                chipView.setDelegate(this)
                vSubItems.addView(chipView)
            }
        }
        if (selectedCount <= 0) {
            this.lCount.visibility = GONE
        } else {
            this.lCount.visibility = VISIBLE
            this.lCount.text = selectedCount.toString()
        }
        it.chips.forEachIndexed { index, chip ->
            (vSubItems.getChildAt(index) as? ItemChipView)?.configure(filter, chip)
        }
    }

    /**
     * Method which provide to get arrow.
     * @param it ChipSectionModel instance.
     * @return Drawable? instance.
     */
    private fun getArrow(it: ChipSectionModel): Drawable? =
        when (val res = SdkChips.Managers.context.context?.resources) {
            null -> null
            else -> when (it.isExpanded) {
                true -> ResourcesCompat.getDrawable(res, R.drawable.ic_sdk_chips_arrow_down, null)
                false -> ResourcesCompat.getDrawable(res, R.drawable.ic_sdk_chips_arrow_right, null)
            }
        }

    /**
     * Method which provide the on click functional.
     * @param it View view.
     */
    override fun onClick(it: View?) = when (it?.id) {
        R.id.view_header -> {
            section.switchExpanded()
            ChipsManagerImpl.update(filter, section)
            configure(filter, section)
        }

        else -> Unit
    }

    /**
     * Method which provide action when chip selection was changed.
     * @param it ChipModel instance.
     */
    override fun onChipSelectChanged(it: ChipModel) {
        this.section.chips.firstOrNull { chip -> chip.id == it.id }?.isSelected = it.isSelected
        this.onApplyInterface(section)
    }

}