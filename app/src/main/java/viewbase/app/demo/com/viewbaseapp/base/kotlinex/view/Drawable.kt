package viewbase.app.demo.com.viewbaseapp.base.kotlinex.view

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.ShapeDrawable
import android.os.Build
import android.support.annotation.ColorInt

fun Drawable.changeColorBackground(@ColorInt colorToSet: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        when (this) {
            is ShapeDrawable -> {
                val shapeDrawable: ShapeDrawable = this.mutate() as ShapeDrawable
                shapeDrawable.paint.color = colorToSet
            }
            is GradientDrawable -> {
                val gradientDrawable: GradientDrawable = this.mutate() as GradientDrawable
                gradientDrawable.setColor(colorToSet)
            }
            is ColorDrawable -> {
                val colorDrawable: ColorDrawable = this.mutate() as ColorDrawable
                colorDrawable.color = colorToSet
            }
        }
    }
}