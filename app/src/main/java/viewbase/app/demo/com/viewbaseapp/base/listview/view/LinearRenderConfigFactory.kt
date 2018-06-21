package viewbase.app.demo.com.viewbaseapp.base.listview.view

import android.content.Context
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

class LinearRenderConfigFactory(private val input: Input) : RecyclerViewController.RenderConfigFactory {
    override fun create(): RecyclerViewController.RenderConfig {
        val orientation: Int = getOrientation(input.orientation)
        val layoutManager = LinearLayoutManager(input.context, orientation, input.reverse)
        return RecyclerViewController.RenderConfig(layoutManager, input.animator, input.viewHolderSizer, input.decoration, input.loadMoreConfig)
    }

    private fun getOrientation(orientation: Orientation): Int =
            when (orientation) {
                Orientation.HORIZONTAL -> LinearLayoutManager.HORIZONTAL
                Orientation.VERTICAL -> LinearLayoutManager.VERTICAL
            }

    class Input(val context: Context, val orientation: Orientation, val reverse: Boolean = false, val decoration: RecyclerView.ItemDecoration? = null, val loadMoreConfig: RecyclerViewController.LoadMoreConfig? = null, val animator: RecyclerView.ItemAnimator = DefaultItemAnimator(), val viewHolderSizer: ViewSizer? = null)
    enum class Orientation {
        HORIZONTAL, VERTICAL
    }
}