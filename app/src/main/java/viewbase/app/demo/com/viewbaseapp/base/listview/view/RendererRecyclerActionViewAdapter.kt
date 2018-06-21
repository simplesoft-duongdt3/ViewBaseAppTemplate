package viewbase.app.demo.com.viewbaseapp.base.listview.view

import com.github.vivchar.rendererrecyclerviewadapter.RendererRecyclerViewAdapter
import com.github.vivchar.rendererrecyclerviewadapter.ViewHolder

class RendererRecyclerActionViewAdapter : RendererRecyclerViewAdapter() {
    var viewAction: ViewAction? = null

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any?>?) {
        super.onBindViewHolder(holder, position, payloads)
        viewAction?.action(holder.itemView)
    }
}