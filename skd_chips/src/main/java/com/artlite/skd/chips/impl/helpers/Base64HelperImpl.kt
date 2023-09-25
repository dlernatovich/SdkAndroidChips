package com.artlite.skd.chips.impl.helpers

import android.content.Context
import android.util.Base64
import com.artlite.skd.chips.facade.helpers.Base64Helper

/**
 * Implementation of the [Base64Helper].
 */
internal object Base64HelperImpl : Base64Helper {

    /**
     * Method which provide the create functional.
     * @param context Context instance.
     */
    override fun onCreate(context: Context) {}

    /**
     * Method which provide the destroy functional.
     */
    override fun onDestroy() {}

    /**
     * Method which provide to convert [String] to Base64 [String].
     * @param it String original value.
     * @return String Base64 value.
     */
    override fun toBase64(it: String): String {
        return Base64.encodeToString(it.toByteArray(), Base64.NO_WRAP)
    }

    /**
     * Method which provide to convert Base64 [String] to [String].
     * @param it String Base64 value.
     * @return String original value.
     */
    override fun fromBase64(it: String): String {
        return String(Base64.decode(it.toByteArray(), Base64.NO_WRAP))
    }

}