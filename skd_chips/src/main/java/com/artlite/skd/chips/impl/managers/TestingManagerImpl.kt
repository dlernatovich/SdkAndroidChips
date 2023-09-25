package com.artlite.skd.chips.impl.managers

import android.app.Application
import android.content.Context
import com.artlite.skd.chips.facade.managers.TestingManager
import com.artlite.skd.chips.facade.models.Identifiable
import com.artlite.skd.chips.facade.models.Jsonable
import com.artlite.skd.chips.facade.models.createId
import com.artlite.skd.chips.facade.models.fromJson
import com.artlite.skd.chips.facade.models.toJson
import com.artlite.skd.chips.impl.models.ChipFilterModel
import com.artlite.skd.chips.impl.models.ChipModel
import com.artlite.skd.chips.impl.models.ChipSectionModel
import com.artlite.skd.chips.impl.models.PreferenceModel
import java.util.UUID

/**
 * Implementation of the [TestingManager].
 */
internal object TestingManagerImpl: TestingManager {

    /** Instance of the [ChipFilterModel]. */
    private val filter = ChipFilterModel("1.0.0", null, "Admin", "192.168.1.1:6935")

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

    /**
     * Method which provide the create functional.
     * @param context Context instance.
     */
    override fun onCreate(context: Context) = when(val app = context as? Application) {
        null -> Unit
        else -> {
            val preferenceModel = PreferenceModel(app, filter, true)
            val group = preferenceModel.get()
                ?: ChipSectionModel(null, android.R.string.cut, defaultChips)
            preferenceModel.clear()
            preferenceModel.put(group)
            val preferencesModel = preferenceModel.get<ChipSectionModel>()
            println(preferencesModel)
        }
    }

    /**
     * Method which provide the destroy functional.
     */
    override fun onDestroy() {}

}