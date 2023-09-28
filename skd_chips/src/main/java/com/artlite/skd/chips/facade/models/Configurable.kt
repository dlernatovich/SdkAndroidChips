package com.artlite.skd.chips.facade.models

/**
 * Interface which provide the configurable functional.
 * @param T item type value.
 */
interface Configurable<T, K> {

    /**
     * Method which provide the configure functional.
     * @param it T configure file.
     * @param args Array<out K> params.
     */
    fun configure(it: T, vararg args: K)

}