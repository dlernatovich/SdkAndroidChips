package com.artlite.skd.chips.impl.managers

import android.app.Application
import android.content.Context
import com.artlite.skd.chips.facade.abs.SdkChipsCallbacks
import com.artlite.skd.chips.facade.managers.ChipsManager
import com.artlite.skd.chips.impl.models.ChipFilterModel
import com.artlite.skd.chips.impl.models.ChipModel
import com.artlite.skd.chips.impl.models.ChipSectionModel
import com.artlite.skd.chips.impl.models.ChipsModel
import com.artlite.skd.chips.impl.models.PreferenceModel
import com.artlite.skd.chips.impl.models.update
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
    override fun onCreate(context: Context) = when (val app = context as? Application) {
        null -> Unit
        else -> {
            appRef = WeakReference(app)
        }
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
     * @return ChipsModel instance
     */
    override fun get(it: ChipFilterModel): ChipsModel? = searchMap[it.id]?.get()

    /**
     * Method which provide to get of the [ChipsModel].
     * @param it ChipFilterModel instance.
     * @param default ChipsModel instance.
     * @return ChipsModel instance
     */
    override fun get(it: ChipFilterModel, default: ChipsModel): ChipsModel =
        when (val pref = searchMap[it.id]) {
            null -> {
                searchMap[it.id] = PreferenceModel(app, it, true)
                get(it, default)
            }

            else -> when (val model = pref.get<ChipsModel>()) {
                null -> {
                    pref.set(default)
                    get(it, default)
                }

                else -> compareAndGet(pref, model, default)
            }
        }

    /**
     * Method which provide to set of the [ChipSectionModel].
     * @param it ChipFilterModel instance.
     * @param items ChipsModel instance.
     * @return Boolean if it was set.
     */
    override fun set(it: ChipFilterModel, items: ChipsModel): Boolean =
        when (val pref = searchMap[it.id]) {
            null -> false
            else -> {
                pref.set(items)
                notifyUpdate(it, items)
                true
            }
        }

    /**
     * Method which provide the update functional.
     * @param it ChipFilterModel instance.
     * @param model ChipsModel instance.
     * @return if it was updated.
     */
    override fun update(it: ChipFilterModel, model: ChipsModel): Boolean =
        when (val pref = searchMap[it.id]) {
            null -> false
            else -> {
                pref.set(model)
                notifyUpdate(it, model)
                true
            }
        }

    /**
     * Method which provide the update functional.
     * @param it ChipFilterModel instance.
     * @param model ChipSectionModel instance.
     * @return if it was updated.
     */
    override fun update(it: ChipFilterModel, model: ChipSectionModel): Boolean =
        when (val pref = searchMap[it.id]) {
            null -> false
            else -> when (val chips = pref.get<ChipsModel>()) {
                null -> false
                else -> {
                    chips.update(model)
                    pref.set(chips)
                    notifyUpdate(it, chips)
                    true
                }
            }
        }

    /**
     * Method which provide the update functional.
     * @param it ChipFilterModel instance.
     * @param model ChipModel instance.
     * @return if it was updated.
     */
    override fun update(it: ChipFilterModel, model: ChipModel): Boolean =
        when (val pref = searchMap[it.id]) {
            null -> false
            else -> when (val chips = pref.get<ChipsModel>()) {
                null -> false
                else -> {
                    chips.update(model)
                    pref.set(chips)
                    notifyUpdate(it, chips)
                    true
                }
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

    /**
     * Method which provide the notify update.
     * @param it ChipFilterModel filter model.
     * @param items ChipsModel value.
     */
    private fun notifyUpdate(it: ChipFilterModel, items: ChipsModel? = null) {
        val callback = notificationMap[it.id]?.get() ?: return
        val result = items ?: searchMap[it.id]?.get<ChipsModel>() ?: return
        callback.onChipsUpdate(it, result)
    }

    /**
     * Method which provide to compare and get chips model (in case if resources will be updated).
     * @param pref PreferenceModel instance.
     * @param model ChipsModel from shared preferences.
     * @param default ChipsModel from default.
     * @return ChipsModel instance.
     */
    private fun compareAndGet(
        pref: PreferenceModel,
        model: ChipsModel,
        default: ChipsModel
    ): ChipsModel {
        var needUpdate = false
        var chips = model
        // Compare sections.
        chips.sections.forEachIndexed { sectionIndex, chipSectionModel ->
            // Find section.
            val section = default.sections[sectionIndex]
            // Compare icon.
            if (chipSectionModel.icon != section.icon) {
                needUpdate = true
                chipSectionModel.icon = section.icon
            }
            // Compare text.
            if (chipSectionModel.text != section.text) {
                needUpdate = true
                chipSectionModel.text = section.text
            }
            // Compare chips.
            chipSectionModel.chips.forEachIndexed { chipIndex, chipModel ->
                // Find chip.
                val chip = section.chips.elementAt(chipIndex)
                // Compare icon.
                if (chipModel.icon != chip.icon) {
                    needUpdate = true
                    chipModel.icon = chip.icon
                }
                // Compare text.
                if (chipModel.text != chip.text) {
                    needUpdate = true
                    chipModel.text = chip.text
                }
            }
        }
        // Update if needed.
        if (needUpdate) {
            pref.set(chips)
        }
        // Return chips.
        return chips
    }

}