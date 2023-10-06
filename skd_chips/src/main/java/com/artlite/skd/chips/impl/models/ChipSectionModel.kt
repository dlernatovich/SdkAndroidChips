package com.artlite.skd.chips.impl.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.artlite.skd.chips.facade.models.Displayable
import com.artlite.skd.chips.facade.models.Expandable
import com.artlite.skd.chips.facade.models.Identifiable
import com.artlite.skd.chips.facade.models.createId

/**
 * Class which provide the chip section model.
 * @property id String id for section.
 * @property icon Int? value.
 * @property text Int value.
 * @property isExpanded Boolean if section is expanded.
 * @property chips Set<ChipModel> list of chips.
 * @constructor
 */
class ChipSectionModel(
    override val id: String,
    @DrawableRes override var icon: Int? = null,
    @StringRes override var text: Int,
    override var isExpanded: Boolean = false,
    val chips: Set<ChipModel>,
): Identifiable, Displayable, Expandable {

    /**
     * Constructor with parameters.
     * @param icon Int? value.
     * @param text Int value.
     * @param chips Set<ChipModel> array of the [ChipModel].
     * @constructor
     */
    constructor(icon: Int?, text: Int, chips: Set<ChipModel>) : this(
        id = Identifiable.createId(),
        icon = icon,
        text = text,
        isExpanded = false,
        chips = chips
    )

    /**
     * Method which provide the equals functional.
     * @param other Any? model.
     * @return Boolean if it the same.
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as ChipSectionModel
        if (id != other.id) return false
        return true
    }

    /**
     * Method which provide the hash code functional.
     * @return Int hash code value.
     */
    override fun hashCode(): Int {
        return id.hashCode()
    }

}

/**
 * Method which provide to clear selected items.
 * @receiver ChipSectionModel
 */
fun ChipSectionModel.clearSelected() = chips.forEach {
    it.isSelected = false
}

/**
 * Method which provide to get the selected count value.
 * @receiver ChipSectionModel receiver.
 * @return Int value of the selected count.
 */
fun ChipSectionModel.getSelectedCount(): Int = chips.count { it.isSelected }