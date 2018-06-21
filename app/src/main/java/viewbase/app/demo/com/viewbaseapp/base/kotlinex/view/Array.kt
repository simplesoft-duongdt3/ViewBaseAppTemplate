package kotlinex.view

import android.view.View

fun Array<View>.showViews() {
    forEach { view -> view.visibility = View.VISIBLE }
}

fun Array<View>.hideViews() {
    forEach { view -> view.visibility = View.GONE }
}