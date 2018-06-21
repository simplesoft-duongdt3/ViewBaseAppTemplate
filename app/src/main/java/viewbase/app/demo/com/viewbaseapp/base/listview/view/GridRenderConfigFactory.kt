package viewbase.app.demo.com.viewbaseapp.base.listview.view

import android.content.Context
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView

class GridRenderConfigFactory(private val input: Input) : RecyclerViewController.RenderConfigFactory {
    override fun create(): RecyclerViewController.RenderConfig {
        val spanCount: Int = input.spanCount
        val layoutManager = GridLayoutManager(input.context, spanCount)
        layoutManager.spanSizeLookup = input.spanSizeLookup
        return RecyclerViewController.RenderConfig(layoutManager, input.animator, input.viewHolderSizer, input.decoration, input.loadMoreConfig)
    }

    class Input(val context: Context, val spanCount: Int, val spanSizeLookup: GridLayoutManager.SpanSizeLookup = GridLayoutManager.DefaultSpanSizeLookup(), val decoration: RecyclerView.ItemDecoration? = null, val loadMoreConfig: RecyclerViewController.LoadMoreConfig? = null, val animator: RecyclerView.ItemAnimator = DefaultItemAnimator(), val viewHolderSizer: ViewSizer? = null)
}