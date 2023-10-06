package com.artlite.skd.chips.impl.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.artlite.skd.chips.facade.models.Displayable
import com.artlite.skd.chips.facade.models.Identifiable
import com.artlite.skd.chips.facade.models.Jsonable
import com.artlite.skd.chips.facade.models.Versionable
import com.artlite.skd.chips.facade.models.createId

/**
 * Model for chip representation.
 * @property id String value.
 * @property icon Int? value.
 * @property text Int value.
 * @property isSelected Boolean value.
 * @constructor
 */
class ChipModel(
    override val id: String,
    @DrawableRes override var icon: Int?,
    @StringRes override var text: Int,
    override val version: String,
    var isSelected: Boolean = false
) : Identifiable, Displayable, Versionable, Jsonable {

    /**
     * Constructor with parameters
     * @param icon Int? icon value.
     * @param text Int value.
     * @param version String value.
     * @constructor
     */
    constructor(icon: Int?, text: Int, version: String) : this(
        id = Identifiable.createId(),
        icon = icon,
        text = text,
        version = version
    )

    /**
     * Method which provide the equals functional.
     * @param other Any? model.
     * @return Boolean if it the same.
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as ChipModel
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

    /**
     * Method which provide to switch selected.
     */
    fun switchSelected() {
        this.isSelected = !isSelected
    }

}