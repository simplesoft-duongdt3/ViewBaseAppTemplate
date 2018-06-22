package viewbase.app.demo.com.viewbaseapp.base.resource

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.support.annotation.*
import android.support.v4.content.ContextCompat

class AndroidResourceManager(private val context : Context) : ResourceManager {
    override fun getString(@StringRes stringId: Int) : String = context.getString(stringId)

    override fun getInt(@IntegerRes intId:  Int) : Int = context.resources.getInteger(intId)

    override fun getDrawable(@DrawableRes drawableId: Int): Drawable = ContextCompat.getDrawable(context, drawableId)!!

    @ColorInt
    override fun getColor(@ColorRes colorId: Int): Int = ContextCompat.getColor(context, colorId)

    override fun pxToDp(px: Int): Int = (px.toFloat() / Resources.getSystem().displayMetrics.density).toInt()

    override fun dpToPx(dp: Int): Int = (dp.toFloat() * Resources.getSystem().displayMetrics.density).toInt()
}