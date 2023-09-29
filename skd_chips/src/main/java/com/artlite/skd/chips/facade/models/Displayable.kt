package com.artlite.skd.chips.facade.models

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.content.res.AppCompatResources
import com.artlite.skd.chips.core.SdkChips

/**
 * Interface which provide the display functional.
 * @property icon Int? value.
 * @property text Int value.
 */
interface Displayable {
    @get:DrawableRes val icon: Int?
    @get:StringRes val text: Int
    companion object
}

/**
 * Method which provide to get the [Drawable].
 * @receiver Displayable receiver.
 * @return Drawable? instance.
 */
@SuppressLint("UseCompatLoadingForDrawables")
fun Displayable.getIconDrawable(): Drawable? = when(icon) {
    null -> null
    else -> when(val act = SdkChips.Managers.activity.topActivity) {
        null -> null
        else -> AppCompatResources.getDrawable(act, icon!!)
    }
}

/**
 * Method which provide to get the [String] value of the text.
 * @receiver Displayable receiver.
 * @return String? text value.
 */
fun Displayable.getTextString(): String? {
    return SdkChips.Managers.activity.topActivity?.resources?.getString(text)
}