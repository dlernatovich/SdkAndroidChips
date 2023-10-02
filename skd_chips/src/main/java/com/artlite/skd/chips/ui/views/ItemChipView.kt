package com.artlite.skd.chips.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.res.ResourcesCompat
import com.artlite.skd.chips.facade.models.Configurable
import com.artlite.skd.chips.facade.models.getIconDrawable
import com.artlite.skd.chips.facade.models.getTextString
import com.artlite.skd.chips.impl.managers.ChipsManagerImpl
import com.artlite.skd.chips.impl.models.ChipFilterModel
import com.artlite.skd.chips.impl.models.ChipModel
import com.artlite.skd.chips.impl.models.ChipSectionModel
import com.artlite.skd.chips.impl.models.getSelectedCount
import com.artlite.skd.chips.ui.abs.AbsView
import com.artlite.skd.ships.R
import com.google.android.material.card.MaterialCardView
import java.lang.ref.WeakReference

/**
 * Delegate for the [ItemChipView].
 */
internal interface ItemChipViewDelegate {

    /**
     * Method which provide action when chip selection was changed.
     * @param it ChipModel instance.
     */
    fun onChipSelectChanged(it: ChipModel)

}

/**
 * View for the chip section.
 * @constructor
 */
internal class ItemChipView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AbsView(context, attrs),
    Configurable<ChipFilterModel, ChipModel>,
    OnClickListener {
    // View.
    private val vContainer by lazy { find<MaterialCardView>(R.id.view_container) }
    private val iChip by lazy { find<AppCompatImageView>(R.id.ic_chip) }
    private val lName by lazy { find<AppCompatTextView>(R.id.label_name) }
    // Filter.
    private var filterRef: WeakReference<ChipFilterModel>? = null
    private val filter get() = filterRef?.get()!!
    // Model.
    private var modelRef: WeakReference<ChipModel>? = null
    private val model get()= modelRef?.get()!!
    // Delegate.
    private var delegateRef: WeakReference<ItemChipViewDelegate>? = null
    private val delegate get() = delegateRef?.get()

    /** Get layout ID functional. */
    override fun getLayoutId(): Int = R.layout.view_sdk_chips_item_chip

    /** On create view functional. */
    override fun onCreateView() {}

    /** Method which provide the dismissed functional. */
    override fun onDismissed() {}

    /**
     * Method which provide to st delegate.
     * @param it ItemChipViewDelegate instance.
     */
    fun setDelegate(it: ItemChipViewDelegate) {
        this.delegateRef = WeakReference(it)
    }

    /**
     * Method which provide the configure functional.
     * @param it T configure file.
     * @param args Array<out K> params.
     */
    override fun configure(it: ChipFilterModel, vararg args: ChipModel) {
        this.filterRef = WeakReference(it)
        this.modelRef = WeakReference(args.first())
        this.onApplyInterface(model)
    }

    /**
     * Method which provide the apply interface.
     * @param it ChipModel instance.
     */
    private fun onApplyInterface(it: ChipModel) {
        val icon = it.getIconDrawable()
        this.iChip.setImageDrawable(icon)
        this.lName.text = it.getTextString()
        this.vContainer.setOnClickListener(this)
        if (icon == null) {
            this.iChip.visibility = GONE
        } else {
            this.iChip.visibility = VISIBLE
        }
        if (it.isSelected) {
            vContainer.setBackgroundColor(ResourcesCompat.getColor(context.resources, R.color.sdk_chip_main_10, null))
        } else {
            vContainer.setBackgroundColor(ResourcesCompat.getColor(context.resources, android.R.color.transparent, null))
        }
    }

    /**
     * Method which provide the click functional.
     * @param it View instance.
     */
    override fun onClick(it: View?) {
        this.model.switchSelected()
        ChipsManagerImpl.update(filter, model)
        this.onApplyInterface(model)
        this.delegate?.onChipSelectChanged(model)
    }
}