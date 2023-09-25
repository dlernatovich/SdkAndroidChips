package com.artlite.skd.chips.impl.models

import com.artlite.skd.chips.core.SdkChips
import com.artlite.skd.chips.facade.models.Identifiable
import com.artlite.skd.chips.facade.models.Jsonable
import com.artlite.skd.chips.facade.models.Versionable
import kotlin.math.max

/**
 * Chip filter model.
 * @property id String value.
 * @constructor
 */
class ChipFilterModel(
    override val version: String,
    length: Int?,
    vararg filter: String?
) : Identifiable, Versionable, Jsonable {
    override val id: String = createId(length, version, *filter)

    /**
     * Method which provide the equals functional.
     * @param other Any? model.
     * @return Boolean if it the same.
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as ChipFilterModel
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
 * Method which provide to create ID.
 * @receiver ChipFilterModel receiver.
 * @param filter Array<out String> filters array.
 * @return String generated ID.
 */
private fun createId(length: Int?, version: String, vararg filter: String?): String {
    val text = filter.toMutableList().apply { add(version) }.filterNotNull()
        .joinToString("") {
            SdkChips.Helpers.base64.toBase64(it).replace("=", "")
        }
    return when(length) {
        null -> text
        else -> text.take(max(length, 0))
    }
}