package com.artlite.skd.chips.facade.models

import java.util.UUID

/**
 * Interface which provide the identifiable functional.
 * @property id String value.
 */
interface Identifiable {
    val id: String
    companion object
}

/**
 * Method which provide to create ID.
 * @receiver Identifiable receiver.
 * @return String created ID value.
 */
fun Identifiable.Companion.createId(): String { return UUID.randomUUID().toString() }