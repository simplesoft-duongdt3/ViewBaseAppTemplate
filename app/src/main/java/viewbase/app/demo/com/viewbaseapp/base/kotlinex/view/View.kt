package viewbase.app.demo.com.viewbaseapp.base.kotlinex.view

import android.view.View
import android.view.ViewTreeObserver
import viewbase.app.demo.com.viewbaseapp.base.listview.view.ViewSize

inline fun <T : View> T.afterMeasured(crossinline f: T.(ViewSize) -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            if (measuredWidth > 0 && measuredHeight > 0) {
                viewTreeObserver.removeOnGlobalLayoutListener(this)
                val viewSize = ViewSize(measuredWidth, measuredHeight)
                f(viewSize)
            }
        }
    })
}


inline fun <T : View> T.afterMeasuredSize(crossinline f: T.(ViewSize) -> Unit) {
    this.afterMeasured { viewSize ->
        f(viewSize)
    }
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.isVisible(): Boolean {
    return this.visibility == View.VISIBLE
}

fun View.isGone(): Boolean {
    return this.visibility == View.GONE
}

fun View.isInvisible(): Boolean {
    return this.visibility == View.INVISIBLE
}
