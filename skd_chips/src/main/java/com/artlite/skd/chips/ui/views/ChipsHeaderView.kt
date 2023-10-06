package com.artlite.skd.chips.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.View.OnClickListener
import androidx.core.view.children
import com.artlite.skd.chips.core.SdkChips
import com.artlite.skd.chips.facade.abs.SdkChipsCallbacks
import com.artlite.skd.chips.facade.models.Configurable
import com.artlite.skd.chips.impl.managers.ChipsManagerImpl
import com.artlite.skd.chips.impl.models.ChipFilterModel
import com.artlite.skd.chips.impl.models.ChipsModel
import com.artlite.skd.chips.impl.models.clearSelected
import com.artlite.skd.chips.impl.models.getSelectedCount
import com.artlite.skd.chips.impl.models.update
import com.artlite.skd.chips.ui.abs.AbsView
import com.artlite.skd.chips.ui.activities.FilterActivity
import com.artlite.skd.chips.ui.activities.show
import com.artlite.skd.ships.R
import com.google.android.flexbox.FlexboxLayout
import java.lang.ref.WeakReference

/**
 * Delegate for the [ChipsHeaderView].
 */
interface ChipsHeaderViewDelegate {

    /**
     * Method which provide the action when chips view was updated.
     * @param filter ChipFilterModel instance.
     * @param chips ChipsModel instance.
     */
    fun chipsHeaderViewUpdated(filter: ChipFilterModel, chips: ChipsModel)

}

/**
 * View which provide to display chips header.
 * @constructor
 */
class ChipsHeaderView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AbsView(context, attrs),
    Configurable<ChipFilterModel, ChipsModel>,
    SdkChipsCallbacks.ChipsUpdate {

    /** Instance of the chip manager. */
    private val chipsManager get() = SdkChips.Managers.chips

    /** Instance of the [ChipFilterModel]. */
    private lateinit var filter: ChipFilterModel

    /** Instance of the [ChipsModel]. */
    private lateinit var items: ChipsModel

    /** Instance of the [FlexboxLayout]. */
    private val viewFlexBox: FlexboxLayout by lazy { find(R.id.sdk_chips_flexbox_view) }

    /** Instance of the [ItemFilterView]. */
    private val viewItemFilter by lazy { ItemFilterView(context) }

    /** [WeakReference] of the [ChipsHeaderViewDelegate]. */
    private var delegateRef: WeakReference<ChipsHeaderViewDelegate>? = null

    /** Instance of the [ChipsHeaderViewDelegate]. */
    private val delegate get() = delegateRef?.get()

    /** [Int] value of the items count. */
    private val count get() = viewFlexBox.childCount

    /** Get layout ID functional. */
    override fun getLayoutId(): Int = R.layout.view_sdk_chips_header

    /** On create view functional. */
    override fun onCreateView() {}

    /** Method which provide the dismissed functional. */
    override fun onDismissed() {}

    /**
     * Method which provide the configure functional.
     * @param it T configure file.
     * @param args Array<out K> params.
     */
    override fun configure(it: ChipFilterModel, vararg args: ChipsModel) = main {
        this.filter = it
        this.items = chipsManager.get(it, args.first())
        this.chipsManager.subscribe(it, this)
        this.onChipsUpdate(filter, this.items)
    }

    /**
     * Method which provide to update of the chips.
     * @param filter ChipFilterModel instance.
     * @param chips ChipsModel instance.
     */
    override fun onChipsUpdate(filter: ChipFilterModel, chips: ChipsModel): Unit = when (count) {
        // Init view when item count is 0.
        0 -> {
            this.viewItemFilter.setOnClickListener(onFilterClicked)
            this.viewFlexBox.addView(viewItemFilter)
            chips.sections.forEach {
                val view = ItemSectionView(context)
                view.configure(it)
                view.setOnClickListener(onSectionClicked)
                viewFlexBox.addView(view)
            }
        }
        // Update views.
        else -> {
            this.items = chips
            chips.sections.forEachIndexed { index, model ->
                (viewFlexBox.getChildAt(index + 1) as? ItemSectionView)?.configure(model)
            }
        }
    }.also { onUpdateVisibility() }.also { delegate?.chipsHeaderViewUpdated(filter, chips) }

    /**
     * Method which provide to set [ChipsHeaderViewDelegate].
     * @param it ChipsHeaderViewDelegate instance.
     */
    fun setDelegate(it: ChipsHeaderViewDelegate) {
        this.delegateRef = WeakReference(it)
    }

    /**
     * Method which provide the visibility.
     */
    private fun onUpdateVisibility() {
        this.viewFlexBox.children.forEach {
            val view = it as? ItemSectionView
            val model = view?.section
            val selectedCount = model?.getSelectedCount() ?: 0
            if (selectedCount > 0) {
                view?.visibility = VISIBLE
            } else {
                view?.visibility = GONE
            }
        }
    }

    /**
     * On filter clicked functional.
     */
    private val onFilterClicked = OnClickListener {
        playHaptic()
        FilterActivity.show(filter)
    }

    /**
     * On filter clicked functional.
     */
    private val onSectionClicked = OnClickListener {
        val view = (it as? ItemSectionView)
        val model = view?.section
        if (model != null) {
            playHaptic()
            model.clearSelected()
            items.update(model)
            ChipsManagerImpl.update(filter, items)
        }
    }

}