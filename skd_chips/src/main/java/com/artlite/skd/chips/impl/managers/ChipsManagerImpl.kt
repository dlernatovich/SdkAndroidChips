package com.artlite.skd.chips.impl.managers

import android.app.Application
import android.content.Context
import com.artlite.skd.chips.facade.abs.SdkChipsCallbacks
import com.artlite.skd.chips.facade.managers.ChipsManager
import com.artlite.skd.chips.impl.models.ChipFilterModel
import com.artlite.skd.chips.impl.models.ChipSectionModel
import com.artlite.skd.chips.impl.models.ChipsModel
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

    /** Search [Map]. */
    private val searchMap: MutableMap<String, PreferenceModel> = mutableMapOf()

    /** Notifications [Map]. */
    private val notificationMap: MutableMap<String, WeakReference<SdkChipsCallbacks.ChipsUpdate>> =
        mutableMapOf()

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
        searchMap.clear()
        notificationMap.clear()
    }

    /**
     * Method which provide to get of the [ChipsModel].
     * @param it ChipFilterModel instance.
     * @param default ChipsModel instance.
     * @return ChipsModel instance
     */
    override fun get(it: ChipFilterModel, default: ChipsModel): ChipsModel =
        when(val pref = searchMap[it.id]) {
            null -> {
                searchMap[it.id] = PreferenceModel(app, it, true)
                get(it, default)
            }
            else -> when(val model = pref.get<ChipsModel>()) {
                null -> {
                    pref.set(default)
                    get(it, default)
                }
                else -> model
            }
        }

    /**
     * Method which provide to set of the [ChipSectionModel].
     * @param it ChipFilterModel instance.
     * @param items ChipsModel instance.
     * @return Boolean if it was set.
     */
    override fun set(it: ChipFilterModel, items: ChipsModel): Boolean =
        when(val pref = searchMap[it.id]) {
            null -> false
            else -> {
                pref.set(items)
                notificationMap[it.id]?.get()?.onChipsUpdate(it, items)
                true
            }
        }


    /**
     * Method which provide to subscribe for the notifications.
     * @param filter ChipFilterModel instance.
     * @param delegate ChipsUpdate instance.
     */
    override fun subscribe(filter: ChipFilterModel, delegate: SdkChipsCallbacks.ChipsUpdate) {
        this.notificationMap[filter.id] = WeakReference(delegate)
    }
}