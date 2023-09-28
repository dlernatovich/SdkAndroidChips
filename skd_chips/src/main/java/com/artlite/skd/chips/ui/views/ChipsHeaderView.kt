package com.artlite.skd.chips.ui.views

import android.content.Context
import android.util.AttributeSet
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import com.artlite.skd.chips.core.SdkChips
import com.artlite.skd.chips.facade.abs.SdkChipsCallbacks
import com.artlite.skd.chips.facade.models.Configurable
import com.artlite.skd.chips.impl.models.ChipFilterModel
import com.artlite.skd.chips.impl.models.ChipsModel
import com.artlite.skd.chips.ui.abs.AbsView
import com.artlite.skd.ships.R
import com.google.android.flexbox.FlexboxLayout
import java.time.Duration

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
    override fun onChipsUpdate(filter: ChipFilterModel, chips: ChipsModel) =
        when(viewFlexBox.childCount) {
            0 -> {
                chips.sections.forEach {
                    val chip = AppCompatTextView(context)
                    chip.text = it.text.toString() + "|"
                    chip.setOnClickListener { view ->
                        Toast.makeText(context, it.text.toString(), Toast.LENGTH_SHORT).show()
                    }
                    viewFlexBox.addView(chip)
                }
            }
            else -> {

            }
        }

}