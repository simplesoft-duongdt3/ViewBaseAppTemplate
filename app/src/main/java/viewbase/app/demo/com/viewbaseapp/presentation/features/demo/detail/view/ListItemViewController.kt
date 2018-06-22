package viewbase.app.demo.com.viewbaseapp.presentation.features.demo.detail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import com.github.vivchar.rendererrecyclerviewadapter.binder.ViewFinder
import kotlinx.android.synthetic.main.list_item_home.view.*
import viewbase.app.demo.com.viewbaseapp.R
import viewbase.app.demo.com.viewbaseapp.base.listview.model.ViewHolderRenderer
import viewbase.app.demo.com.viewbaseapp.base.listview.view.GridRenderConfigFactory
import viewbase.app.demo.com.viewbaseapp.base.listview.view.RecyclerViewController
import viewbase.app.demo.com.viewbaseapp.base.listview.view.SpaceItemDecoration
import viewbase.app.demo.com.viewbaseapp.base.viewbase.ViewController

class ListItemViewController(bundle: Bundle?) : ViewController(bundle) {
    constructor() : this(null)

    private lateinit var recyclerViewController: RecyclerViewController
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.list_item_home, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View) {
        val input = GridRenderConfigFactory.Input(view.context, 4, decoration = SpaceItemDecoration(4))
        val renderConfig = GridRenderConfigFactory(input).create()
        recyclerViewController = RecyclerViewController(view.rvItems, renderConfig)
        recyclerViewController.setPadding(4)
        recyclerViewController.addViewRenderer(ItemViewHolderRenderer())

        val list = ArrayList<ItemViewHolderModel>()
        for (i in 1..100) {
            list.add(ItemViewHolderModel(i))
        }
        recyclerViewController.setItems(list)
    }

    class ItemViewHolderRenderer : ViewHolderRenderer<ItemViewHolderModel>() {
        override fun getLayoutId(): Int {
            return R.layout.item_view_holder
        }

        override fun getModelClass(): Class<ItemViewHolderModel> {
            return ItemViewHolderModel::class.java
        }

        override fun bindView(model: ItemViewHolderModel, viewFinder: ViewFinder) {
            viewFinder.setText(R.id.tvItem, "Item ${model.index}")
        }
    }

    class ItemViewHolderModel(val index: Int) : ViewModel
}