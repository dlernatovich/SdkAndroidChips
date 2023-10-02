package com.artlite.sdkchipworkspaceandroid.constants

import com.artlite.sdkchipworkspaceandroid.R
import com.artlite.skd.chips.impl.models.ChipFilterModel
import com.artlite.skd.chips.impl.models.ChipModel
import com.artlite.skd.chips.impl.models.ChipSectionModel
import com.artlite.skd.chips.impl.models.ChipsModel

/**
 * Class which provide the chips constants.
 */
object ChipsConstants {

    /**
     * Instance of the [ChipFilterModel].
     */
    val filter = ChipFilterModel("1.0.5", null, "192.168.0.0", "8080")

    /**
     * Instance of the [ChipsModel].
     */
    val chips: ChipsModel = ChipsModel(
        sections = listOf(
            ChipSectionModel(
                icon = null,
                text = R.string.text_type,
                chips = setOf(
                    ChipModel(R.drawable.ic_settings_box, R.string.text_box, "1.0.0"),
                    ChipModel(R.drawable.ic_settings_file, R.string.text_file, "1.0.0"),
                    ChipModel(R.drawable.ic_settings_doc, R.string.text_doc, "1.0.0"),
                )
            ),
            ChipSectionModel(
                icon = null,
                text = R.string.text_schedule,
                chips = setOf(
                    ChipModel(R.drawable.round_calendar_month_24, R.string.text_last_month, "1.0.0"),
                    ChipModel(R.drawable.round_badge_24, R.string.text_assigned, "1.0.0"),
                )
            ),
        )
    )

}