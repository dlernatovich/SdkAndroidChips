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

/**
 * Method which provide to get chips functional.
 * @receiver ChipsModel receiver.
 * @return List<ChipModel> list of chips model.
 */
fun ChipsModel.getChips(): List<ChipModel> = mutableListOf<ChipModel>().also { result ->
    sections.forEach { section -> result.addAll(section.chips) }
}

/**
 * Method which provide to update of the chip model.
 * @receiver ChipsModel receiver.
 * @param it Array<out ChipModel> array of the [ChipModel].
 */
fun ChipsModel.update(vararg it: ChipModel) {
    val allChip = getChips()
    it.forEach { chip ->
        allChip.firstOrNull { item -> item.id == chip.id }?.isSelected = chip.isSelected
    }
}

/**
 * Method which provide to update of the chip section model.
 * @receiver ChipModel receiver.
 * @param it Array<out ChipSectionModel> items.
 */
fun ChipsModel.update(vararg it: ChipSectionModel) {
    it.forEach { section ->
        val item = sections.firstOrNull { item -> item.id == section.id }
        item?.isExpanded = section.isExpanded
        item?.chips?.forEachIndexed { index, chip ->
            val updatedChip = section.chips.firstOrNull { it1 -> it1.id == chip.id }
            if (updatedChip != null) chip.isSelected = updatedChip.isSelected
        }
    }
}