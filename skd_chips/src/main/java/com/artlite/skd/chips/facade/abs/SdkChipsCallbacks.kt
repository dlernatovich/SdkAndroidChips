package com.artlite.skd.chips.facade.abs

import com.artlite.skd.chips.impl.models.ChipFilterModel
import com.artlite.skd.chips.impl.models.ChipsModel

/**
 * Callbacks for the chips SKD.
 */
sealed class SdkChipsCallbacks {

    /**
     * Callback which provide the notify of the chips callback.
     */
    interface ChipsUpdate {

        /**
         * Method which provide to update of the chips.
         * @param filter ChipFilterModel instance.
         * @param chips ChipsModel instance.
         */
        fun onChipsUpdate(filter: ChipFilterModel, chips: ChipsModel)
        
    }

}