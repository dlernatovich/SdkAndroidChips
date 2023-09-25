package com.artlite.skd.chips.facade.managers

import com.artlite.skd.chips.facade.abs.AbsLifecycle
import com.artlite.skd.chips.impl.models.ChipFilterModel
import com.artlite.skd.chips.impl.models.ChipSectionModel

/**
 * Class which provide the chip managements functional.
 */
interface ChipsManager: AbsLifecycle {

    /**
     * Method which provide to get the [ChipSectionModel].
     * @param it ChipFilterModel instance.
     * @param default ChipSectionModel instance.
     * @return ChipSectionModel instance.
     */
    fun get(it: ChipFilterModel, default: ChipSectionModel): ChipSectionModel

    /**
     * Method which provide to get the [ChipSectionModel].
     * @param it String value.
     * @param default ChipSectionModel instance.
     * @return ChipSectionModel instance.
     */
    fun get(it: String, default: ChipSectionModel): ChipSectionModel

    /**
     * Method which provide to get the last search model.
     * @return ChipSectionModel? instance.
     */
    fun getLast(): ChipSectionModel?

}