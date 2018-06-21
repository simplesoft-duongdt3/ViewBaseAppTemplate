package viewbase.app.demo.com.viewbaseapp.base.listview.model

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

class ViewRendererProvider {
    private val viewRendererList = mutableListOf<ViewHolderRenderer<out ViewModel>>()

    fun addViewRenderer(viewHolderRenderer: ViewHolderRenderer<out ViewModel>): ViewRendererProvider {
        viewRendererList.add(viewHolderRenderer)
        return this
    }

    fun getViewRendererList(): List<ViewHolderRenderer<out ViewModel>> {
        return viewRendererList
    }

    fun clear() {
        viewRendererList.clear()
    }
}