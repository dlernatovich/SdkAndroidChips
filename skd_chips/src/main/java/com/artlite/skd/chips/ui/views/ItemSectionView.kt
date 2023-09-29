package com.artlite.skd.chips.ui.views

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.artlite.skd.chips.facade.models.Configurable
import com.artlite.skd.chips.facade.models.getTextString
import com.artlite.skd.chips.impl.models.ChipSectionModel
import com.artlite.skd.chips.impl.models.getSelectedCount
import com.artlite.skd.chips.ui.abs.AbsView
import com.artlite.skd.ships.R
import java.lang.ref.WeakReference

/**
 * View for the chip section.
 * @constructor
 */
internal class ItemSectionView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AbsView(context, attrs), Configurable<ChipSectionModel, ChipSectionModel> {

    /** Instance of the [ChipSectionModel] references. */
    private lateinit var sectionRef: WeakReference<ChipSectionModel>

    /** Instance of the [ChipSectionModel]. */
    val section get() = sectionRef.get()!!

    /** Instance of the [AppCompatTextView]. */
    private val lName by lazy { find<AppCompatTextView>(R.id.label_name) }

    /** Instance of the [AppCompatTextView]. */
    private val lCount by lazy { find<AppCompatTextView>(R.id.label_count) }

    /** Get layout ID functional. */
    override fun getLayoutId(): Int = R.layout.view_sdk_chips_item_section

    /** On create view functional. */
    override fun onCreateView() {}

    /** Method which provide the dismissed functional. */
    override fun onDismissed() {}

    /**
     * Method which provide the configure functional.
     * @param it T configure file.
     * @param args Array<out K> params.
     */
    override fun configure(it: ChipSectionModel, vararg args: ChipSectionModel) {
        this.sectionRef = WeakReference(it)
        this.applyInterface(it)
    }

    /**
     * Method which provide the apply interface.
     * @param it ChipSectionModel instance.
     */
    private fun applyInterface(it: ChipSectionModel) = main {
        this.lName.text = it.getTextString()
        this.lCount.text = it.getSelectedCount().toString()
    }
}