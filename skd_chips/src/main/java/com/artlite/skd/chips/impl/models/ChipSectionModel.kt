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
    @DrawableRes override val icon: Int? = null,
    @StringRes override val text: Int,
    val chips: Set<ChipModel>
): Identifiable, Displayable, Expandable {
    override var isExpanded: Boolean = false

    /**
     * Constructor with parameters.
     * @param icon Int? value.
     * @param text Int value.
     * @param chips Set<ChipModel> array of the [ChipModel].
     * @constructor
     */
    constructor(icon: Int?, text: Int, chips: Set<ChipModel>) :
            this(Identifiable.createId(), icon, text, chips)

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