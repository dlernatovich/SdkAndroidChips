package com.artlite.skd.chips.impl.models

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Base64
import com.artlite.skd.chips.facade.models.Identifiable
import com.artlite.skd.chips.facade.models.Jsonable
import com.google.gson.GsonBuilder

/**
 * Preference model class.
 * @property preferences SharedPreferences instance.
 * @constructor
 */
internal class PreferenceModel {

    /** [String] value of the default key. */
    private val defaultKey: String = "18e2ca61-ecd8-43ff-b05e-365163a02c2c"

    /** Shared Preference field used to save and retrieve JSON string. */
    private val preferences: SharedPreferences

    /** If need base64 formatted */
    private val base64Formatted: Boolean

    /**
     * Default constructor.
     * @param app Application instance.
     * @param name String value.
     * @param base64Formatted Boolean need to format.
     * @constructor
     */
    constructor(app: Application, name: String, base64Formatted: Boolean) {
        this.base64Formatted = base64Formatted
        this.preferences = app.getSharedPreferences(name, Context.MODE_PRIVATE)
    }

    /**
     * Constructor from model.
     * @param app Application instance.
     * @param model ChipFilterModel filter.
     * @param base64Formatted Boolean need to format.
     * @constructor
     */
    constructor(app: Application, model: ChipFilterModel, base64Formatted: Boolean) :
            this(app, model.id, base64Formatted)

    /**
     * Saves object into the Preferences.
     * @param `object` Object of model class (of type [T]) to save
     * @param key Key with which Shared preferences to
     **/
    fun <T> set(`object`: T, key: String = defaultKey) {
        this.clear()
        this.put(`object`, key)
    }

    /**
     * Saves object into the Preferences.
     * @param `object` Object of model class (of type [T]) to save
     * @param key Key with which Shared preferences to
     **/
    fun <T> put(`object`: T, key: String = defaultKey) {
        var jsonString = GsonBuilder().create().toJson(`object`)
        if (base64Formatted) {
            jsonString = Base64.encodeToString(jsonString.toByteArray(), Base64.NO_WRAP)
        }
        this.preferences.edit().putString(key, jsonString).apply()
    }

    /**
     * Used to retrieve object from the Preferences.
     * @param key Shared Preference key with which object was saved.
     **/
    inline fun <reified T: Identifiable> get(key: String = defaultKey): T? {
        var it = preferences.getString(key, null) ?: return null
        if (base64Formatted) {
            it = String(Base64.decode(it.toByteArray(), Base64.NO_WRAP))
        }
        return GsonBuilder().create().fromJson(it, T::class.java)
    }

    /**
     * Method which provide the clear functional.
     */
    fun clear() { this.preferences.edit().clear().apply() }
}