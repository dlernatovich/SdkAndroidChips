package com.artlite.skd.chips.facade.helpers

import com.artlite.skd.chips.facade.abs.AbsLifecycle

/**
 * Helper which provide Base64 functionality.
 */
internal interface Base64Helper: AbsLifecycle {

    /**
     * Method which provide to convert [String] to Base64 [String].
     * @param it String original value.
     * @return String Base64 value.
     */
    fun toBase64(it: String): String

    /**
     * Method which provide to convert Base64 [String] to [String].
     * @param it String Base64 value.
     * @return String original value.
     */
    fun fromBase64(it: String): String

}