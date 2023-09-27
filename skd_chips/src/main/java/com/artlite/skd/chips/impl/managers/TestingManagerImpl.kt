package com.artlite.skd.chips.impl.managers

import android.app.Application
import android.content.Context
import com.artlite.skd.chips.core.SdkChips
import com.artlite.skd.chips.facade.abs.SdkChipsCallbacks
import com.artlite.skd.chips.facade.managers.TestingManager
import com.artlite.skd.chips.facade.models.Identifiable
import com.artlite.skd.chips.facade.models.Jsonable
import com.artlite.skd.chips.facade.models.createId
import com.artlite.skd.chips.facade.models.fromJson
import com.artlite.skd.chips.facade.models.toJson
import com.artlite.skd.chips.impl.models.ChipFilterModel
import com.artlite.skd.chips.impl.models.ChipModel
import com.artlite.skd.chips.impl.models.ChipSectionModel
import com.artlite.skd.chips.impl.models.ChipsModel
import com.artlite.skd.chips.impl.models.PreferenceModel
import java.util.UUID

/**
 * Implementation of the [TestingManager].
 */
internal object TestingManagerImpl: TestingManager, SdkChipsCallbacks.ChipsUpdate {

    /** Instance of the [ChipFilterModel]. */
    private val filter = ChipFilterModel("1.0.2", null, "Admin", "192.168.1.1:6935")

    /** Array of the default [ChipModel]. */
    private val defaultChips get() = setOf(
        ChipModel(android.R.drawable.sym_def_app_icon, android.R.string.ok, "1.0.0"),
        ChipModel(android.R.drawable.sym_def_app_icon, android.R.string.ok, "1.0.0"),
        ChipModel(android.R.drawable.sym_def_app_icon, android.R.string.ok, "1.0.0"),
        ChipModel(android.R.drawable.ic_menu_compass, android.R.string.ok, "1.0.0"),
        ChipModel(android.R.drawable.btn_default, android.R.string.ok, "1.0.0"),
        ChipModel(android.R.drawable.sym_def_app_icon, android.R.string.ok, "1.0.0"),
        ChipModel(android.R.drawable.sym_def_app_icon, android.R.string.ok, "1.0.0"),
    )

    /** Default chip group. */
    private val defaultGroups  get() = ChipsModel(
        sections = listOf(
            ChipSectionModel(null, android.R.string.cut, defaultChips),
            ChipSectionModel(null, android.R.string.cut, defaultChips),
            ChipSectionModel(null, android.R.string.cut, defaultChips),
            ChipSectionModel(null, android.R.string.cut, defaultChips),
            ChipSectionModel(null, android.R.string.cut, defaultChips),
            ChipSectionModel(null, android.R.string.cut, defaultChips),
            ChipSectionModel(null, android.R.string.cut, defaultChips),
            ChipSectionModel(null, android.R.string.cut, defaultChips),
            ChipSectionModel(null, android.R.string.cut, defaultChips),
            ChipSectionModel(null, android.R.string.cut, defaultChips),
        )
    )

    /**
     * Method which provide the create functional.
     * @param context Context instance.
     */
    override fun onCreate(context: Context) = when(val app = context as? Application) {
        null -> Unit
        else -> {
            val items = SdkChips.Managers.chips.get(filter, defaultGroups)
            SdkChips.Managers.chips.subscribe(filter, this)
            items.sections.first().chips.first().switchSelected()
            SdkChips.Managers.chips.set(filter, items)
            Unit
        }
    }

    /**
     * Method which provide the destroy functional.
     */
    override fun onDestroy() {}

    /**
     * Method which provide to update of the chips.
     * @param filter ChipFilterModel instance.
     * @param chips ChipsModel instance.
     */
    override fun onChipsUpdate(filter: ChipFilterModel, chips: ChipsModel) {
        println("Chips was updated: $chips")
    }

}