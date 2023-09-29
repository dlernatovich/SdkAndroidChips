package com.artlite.skd.chips.facade.managers

import com.artlite.skd.chips.facade.abs.AbsLifecycle
import com.artlite.skd.chips.facade.abs.SdkChipsCallbacks
import com.artlite.skd.chips.impl.models.ChipFilterModel
import com.artlite.skd.chips.impl.models.ChipSectionModel
import com.artlite.skd.chips.impl.models.ChipsModel

/**
 * Class which provide the chip managements functional.
 */
interface ChipsManager: AbsLifecycle {

    /**
     * Method which provide to get of the [ChipsModel].
     * @param it ChipFilterModel instance.
     * @return ChipsModel instance
     */
    fun get(it: ChipFilterModel): ChipsModel?

    /**
     * Method which provide to get of the [ChipsModel].
     * @param it ChipFilterModel instance.
     * @param default ChipsModel instance.
     * @return ChipsModel instance
     */
    fun get(it: ChipFilterModel, default: ChipsModel): ChipsModel

    /**
     * Method which provide to set of the [ChipSectionModel].
     * @param it ChipFilterModel instance.
     * @param items ChipsModel instance.
     * @return Boolean if it was set.
     */
    fun set(it: ChipFilterModel, items: ChipsModel): Boolean

    /**
     * Method which provide to subscribe for the notifications.
     * @param filter ChipFilterModel instance.
     * @param delegate ChipsUpdate instance.
     */
    fun subscribe(filter: ChipFilterModel, delegate: SdkChipsCallbacks.ChipsUpdate)

}