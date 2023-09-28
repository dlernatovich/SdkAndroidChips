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
    val filter = ChipFilterModel("1.0.0", null, "192.168.0.0", "8080")

    /**
     * Instance of the [ChipsModel].
     */
    val chips: ChipsModel = ChipsModel(
        sections = listOf(
            ChipSectionModel(
                icon = null,
                text = android.R.string.paste_as_plain_text,
                chips = setOf(
                    ChipModel(android.R.drawable.ic_delete, android.R.string.ok, "1.0.0"),
                    ChipModel(android.R.drawable.ic_menu_compass, android.R.string.paste, "1.0.0"),
                )
            ),
            ChipSectionModel(
                icon = null,
                text = android.R.string.paste_as_plain_text,
                chips = setOf(
                    ChipModel(android.R.drawable.ic_delete, android.R.string.ok, "1.0.0"),
                    ChipModel(android.R.drawable.ic_menu_compass, android.R.string.paste, "1.0.0"),
                )
            ),
            ChipSectionModel(
                icon = null,
                text = android.R.string.paste_as_plain_text,
                chips = setOf(
                    ChipModel(android.R.drawable.ic_delete, android.R.string.ok, "1.0.0"),
                    ChipModel(android.R.drawable.ic_menu_compass, android.R.string.paste, "1.0.0"),
                )
            ),
        )
    )

}