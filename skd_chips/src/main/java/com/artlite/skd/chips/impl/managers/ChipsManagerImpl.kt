package com.artlite.skd.chips.impl.managers

import android.app.Application
import android.content.Context
import com.artlite.skd.chips.facade.managers.ChipsManager
import com.artlite.skd.chips.impl.models.ChipFilterModel
import com.artlite.skd.chips.impl.models.ChipSectionModel
import com.artlite.skd.chips.impl.models.PreferenceModel
import java.lang.ref.WeakReference

/**
 * Implementation of the [ChipsManager].
 */
internal object ChipsManagerImpl : ChipsManager {

    /** Instance of the [WeakReference]. */
    private var appRef: WeakReference<Application>? = null

    /** Instance of the [Application]. */
    private val app: Application get() = appRef?.get()!!

    /** [String] value of the last search ID. */
    private var lastSearchId: String? = null

    /** Search [Map]. */
    private val searchMap: MutableMap<String, PreferenceModel> = mutableMapOf()

    /**
     * Method which provide the create functional.
     * @param context Context instance.
     */
    override fun onCreate(context: Context) = when(val app = context as? Application) {
        null -> Unit
        else -> { appRef = WeakReference(app) }
    }

    /**
     * Method which provide the destroy functional.
     */
    override fun onDestroy() {
        appRef = null
    }

    /**
     * Method which provide to get the [ChipSectionModel].
     * @param it ChipFilterModel instance.
     * @param default ChipSectionModel instance.
     * @return ChipSectionModel instance.
     */
    override fun get(it: ChipFilterModel, default: ChipSectionModel): ChipSectionModel =
        get(it.id, default)

    /**
     * Method which provide to get the [ChipSectionModel].
     * @param it String value.
     * @param default ChipSectionModel instance.
     * @return ChipSectionModel instance.
     */
    override fun get(it: String, default: ChipSectionModel): ChipSectionModel =
        when(val pref = searchMap[it]) {
            null -> {
                searchMap[it] = PreferenceModel(app, it, true)
                get(it, default)
            }
            else -> when(val model = pref.get<ChipSectionModel>()) {
                null -> {
                    pref.set(default)
                    get(it, default)
                }
                else -> {
                    lastSearchId = it
                    model
                }
            }
        }

    /**
     * Method which provide to get the last search model.
     * @return ChipSectionModel? instance.
     */
    override fun getLast(): ChipSectionModel? = when(lastSearchId) {
        null -> null
        else -> searchMap[lastSearchId!!]?.get()
    }

}