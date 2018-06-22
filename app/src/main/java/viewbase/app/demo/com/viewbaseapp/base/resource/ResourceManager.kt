package viewbase.app.demo.com.viewbaseapp.base.resource

import android.graphics.drawable.Drawable
import android.support.annotation.*

interface ResourceManager {
    fun getString(@StringRes stringId: Int) : String

    fun getInt(@IntegerRes intId:  Int) : Int

    fun getDrawable(@DrawableRes drawableId: Int): Drawable

    @ColorInt
    fun getColor(@ColorRes colorId: Int): Int

    fun pxToDp(px: Int): Int

    fun dpToPx(dp: Int): Int
}