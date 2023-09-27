package com.artlite.skd.chips.impl.models

import com.artlite.skd.chips.facade.models.Identifiable
import com.artlite.skd.chips.facade.models.createId

/**
 * Model which provide the chips functional.
 * @property id String value.
 * @property sections List<ChipSectionModel> array of the sections.
 * @constructor
 */
data class ChipsModel(
    override val id: String = Identifiable.createId(),
    val sections: List<ChipSectionModel>
): Identifiable {

    /**
     * Method which provide the equals functional.
     * @param other Any? model.
     * @return Boolean if it the same.
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as ChipsModel
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