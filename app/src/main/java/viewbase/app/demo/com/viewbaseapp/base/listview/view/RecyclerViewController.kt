package viewbase.app.demo.com.viewbaseapp.base.listview.view

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import com.github.vivchar.rendererrecyclerviewadapter.binder.LoadMoreViewBinder
import viewbase.app.demo.com.viewbaseapp.base.kotlinex.view.afterMeasuredSize
import viewbase.app.demo.com.viewbaseapp.base.listview.model.ViewHolderRenderer

class RecyclerViewController(private val recyclerView: RecyclerView, private val renderConfig: RenderConfig) {

    private val viewAdapter: RendererRecyclerActionViewAdapter = RendererRecyclerActionViewAdapter()
    private var isShowingLoadMore = false
    private var itemRvClickedEvent: OnItemRvClickedListener<ViewModel>? = null
    private val items: MutableList<ViewModel> = arrayListOf()

    init {
        recyclerView.afterMeasuredSize { size ->
            viewAdapter.viewAction = renderConfig.viewHolderSizer?.size(size)
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = renderConfig.layoutManager
            renderConfig.itemAnimator?.let {
                recyclerView.itemAnimator = it
            }
            renderConfig.itemDecoration?.let {
                recyclerView.addItemDecoration(it)
            }
            recyclerView.adapter = viewAdapter
            renderConfig.loadMoreConfig?.let {
                configLoadMore(it)
            }

            RvItemClickSupport.addTo(recyclerView).setOnItemClickListener(onItemClickListener)
        }
    }

    fun setOnItemRvClickedListener(mOnItemRvClickedListener: OnItemRvClickedListener<ViewModel>) {
        this.itemRvClickedEvent = mOnItemRvClickedListener
    }

    private var onItemClickListener: RvItemClickSupport.OnItemClickListener = object : RvItemClickSupport.OnItemClickListener {
        override fun onItemClicked(recyclerView: RecyclerView, position: Int, v: View?) {
            if (v != null && position >= 0 && position <= viewAdapter.itemCount) {
                itemRvClickedEvent?.onItemClicked(v, position, viewAdapter.getItem(position))
            }
        }
    }

    private fun configLoadMore(loadMoreConfig: LoadMoreConfig) {
        viewAdapter.registerRenderer(loadMoreConfig.viewRenderer)
        recyclerView.addOnScrollListener(EndlessRecyclerViewScrollListener(recyclerView.layoutManager, loadMoreConfig.loadMoreEvent))
    }

    fun showLoadMore() {
        if (!isShowingLoadMore) {
            recyclerView.post {
                isShowingLoadMore = true
                viewAdapter.showLoadMore()
            }
        }
    }

    private fun hideLoadMore() {
        recyclerView.post {
            isShowingLoadMore = false
            viewAdapter.hideLoadMore()
        }
    }

    fun scrollToPosition(position: Int) {
        recyclerView.scrollToPosition(position)
    }

    fun addViewRenderer(viewHolderRenderer: ViewHolderRenderer<out ViewModel>) {
        viewAdapter.registerRenderer(viewHolderRenderer.createViewBinder())
    }

    fun <T : ViewModel> setItems(items: List<T>) {
        hideLoadMore()
        this.items.clear()
        this.items.addAll(items)
        viewAdapter.setItems(this.items)
    }

    fun <T : ViewModel> addItem(item: T) {
        this.items.add(item)
        viewAdapter.setItems(this.items)
    }

    fun getItem(position: Int): ViewModel {
        return viewAdapter.getItem(position)
    }

    fun notifyDataChanged() {
        viewAdapter.notifyDataSetChanged()
    }

    fun setPadding(padding: Int) {
        recyclerView.setPadding(padding, padding, padding, padding)
    }

    class RenderConfig(val layoutManager: RecyclerView.LayoutManager, val itemAnimator: RecyclerView.ItemAnimator? = null, val viewHolderSizer: ViewSizer? = null, val itemDecoration: RecyclerView.ItemDecoration? = null, val loadMoreConfig: LoadMoreConfig? = null)

    interface RenderConfigFactory {
        fun create(): RenderConfig
    }

    class EndlessRecyclerViewScrollListener(private val layoutManager: RecyclerView.LayoutManager, private val onReachEndItemEvent: () -> Unit) : RecyclerView.OnScrollListener() {

        override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val totalItem = layoutManager.itemCount
            var lastVisibleItem = 0
            if (layoutManager is LinearLayoutManager) {
                lastVisibleItem = layoutManager.findLastVisibleItemPosition()
            } else if (layoutManager is GridLayoutManager) {
                lastVisibleItem = layoutManager.findLastVisibleItemPosition()
            }

            if (lastVisibleItem >= totalItem - 2) {
                onReachEndItemEvent.invoke()
            }
        }
    }

    class LoadMoreConfig(val loadMoreEvent: () -> Unit, val viewRenderer: LoadMoreViewBinder){

}
}