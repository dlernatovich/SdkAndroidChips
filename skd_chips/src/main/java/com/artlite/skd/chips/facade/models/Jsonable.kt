package com.artlite.skd.chips.facade.models

import android.util.Base64
import com.artlite.skd.chips.core.SdkChips
import com.google.gson.GsonBuilder

/**
 * Interface which provide the JSON functionality.
 */
interface Jsonable {
    companion object
}

/**
 * Method which provide to convert model to JSON [String].
 * @return String value.
 */
fun Jsonable.toJson(needBase64: Boolean = false): String = when(needBase64) {
    true -> Base64.encodeToString((GsonBuilder().create().toJson(this)).toByteArray(), Base64.NO_WRAP)
    else -> GsonBuilder().create().toJson(this)
}

/**
 * Method which provide to convert JSON [String] to model.
 * @param it String JSON value.
 * @return T? object model.
 */
inline fun <reified T> Jsonable.Companion.fromJson(
    it: String,
    needBase64: Boolean = false
): T? = when(needBase64) {
    true -> GsonBuilder().create().fromJson(String(Base64.decode(it.toByteArray(), Base64.NO_WRAP)), T::class.java)
    else -> GsonBuilder().create().fromJson(it, T::class.java)
}