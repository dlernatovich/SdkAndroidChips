package com.artlite.skd.chips.facade.models

/**
 * Expandable interface.
 * @property isExpanded Boolean if it is expanded or collapsed.
 */
interface Expandable {
    var isExpanded: Boolean
    companion object
}

/**
 * Method which provide to switch expanded.
 * @receiver Expandable receiver
 * @return Expandable
 */
fun Expandable.switchExpanded() = this.apply {
    isExpanded = !isExpanded
}