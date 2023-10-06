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
    val filter = ChipFilterModel("1.0.7", null, "192.168.0.0", "8080")

    /**
     * Instance of the [ChipsModel].
     */
    val chips: ChipsModel = ChipsModel(
        sections = listOf(
            ChipSectionModel(
                icon = null,
                text = R.string.text_type,
                chips = setOf(
                    ChipModel(
                        id = "44f2fdbc-640f-11ee-8c99-0242ac120002",
                        icon = R.drawable.ic_settings_file,
                        text = R.string.text_file,
                        version = "1.0.0",
                        isSelected = true
                    ),
                    ChipModel(
                        id = "1a266b00-640f-11ee-8c99-0242ac120002",
                        icon = R.drawable.ic_settings_box,
                        text = R.string.text_box,
                        version = "1.0.0",
                        isSelected = true
                    ),
                    ChipModel(
                        id = "5286814c-640f-11ee-8c99-0242ac120002",
                        icon = R.drawable.ic_settings_doc,
                        text = R.string.text_doc,
                        version = "1.0.0"
                    )
                )
            ),
            ChipSectionModel(
                icon = null,
                text = R.string.text_schedule,
                chips = setOf(
                    ChipModel(
                        id = "5a8399e8-640f-11ee-8c99-0242ac120002",
                        icon = R.drawable.round_calendar_month_24,
                        text = R.string.text_last_month,
                        version = "1.0.0"
                    ),
                    ChipModel(
                        id = "66ed62ea-640f-11ee-8c99-0242ac120002",
                        icon = R.drawable.round_badge_24,
                        text = R.string.text_assigned,
                        version = "1.0.0"
                    )
                )
            )
        )
    )

}