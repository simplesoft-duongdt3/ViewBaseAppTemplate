package viewbase.app.demo.com.viewbaseapp.presentation.features.demo.detail.view

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mikepenz.google_material_typeface_library.GoogleMaterial
import com.mikepenz.iconics.IconicsDrawable
import kotlinx.android.synthetic.main.list_item_home.view.*
import org.koin.standalone.inject
import viewbase.app.demo.com.viewbaseapp.R
import viewbase.app.demo.com.viewbaseapp.base.kotlinex.view.gone
import viewbase.app.demo.com.viewbaseapp.base.kotlinex.view.visible
import viewbase.app.demo.com.viewbaseapp.base.listview.view.GridRenderConfigFactory
import viewbase.app.demo.com.viewbaseapp.base.listview.view.RecyclerViewController
import viewbase.app.demo.com.viewbaseapp.base.listview.view.SpaceItemDecoration
import viewbase.app.demo.com.viewbaseapp.base.viewbase.viewcontroller.ViewController
import viewbase.app.demo.com.viewbaseapp.presentation.features.demo.detail.DetailContract
import viewbase.app.demo.com.viewbaseapp.presentation.features.demo.detail.model.UserViewHolderModel

class ListUserViewController(bundle: Bundle?) : ViewController(bundle), DetailContract.View {
    constructor() : this(null)

    private val presenter: DetailContract.Presenter by inject()

    private lateinit var recyclerViewController: RecyclerViewController
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        return inflater.inflate(R.layout.list_item_home, container, false)
    }

    override fun initPostCreateView(view: View) {
        initView(view)
        presenter.attachView(this@ListUserViewController)
        presenter.loadItems()
    }

    private fun initView(view: View) {
        val errorDrawable = IconicsDrawable(view.context, GoogleMaterial.Icon.gmd_error)
                .sizeDp(64)
                .color(Color.RED)
                .paddingDp(4)
        view.ivError.setImageDrawable(errorDrawable)
        view.refreshLayout.setOnRefreshListener {
            presenter.loadItems()
            view.refreshLayout.isRefreshing = false
        }
        val input = GridRenderConfigFactory.Input(view.context, 4, decoration = SpaceItemDecoration(4))
        val renderConfig = GridRenderConfigFactory(input).create()
        recyclerViewController = RecyclerViewController(view.rvItems, renderConfig)
        recyclerViewController.setPadding(4)
        recyclerViewController.addViewRenderer(UserViewHolderRenderer())
    }

    override fun onDestroyView(view: View) {
        super.onDestroyView(view)
        presenter.detachView()
    }

    override fun renderItems(userViewHolderModels: List<UserViewHolderModel>) {
        recyclerViewController.setItems(userViewHolderModels)
        recyclerViewController.notifyDataChanged()
    }

    override fun showLoading() {
        view?.vgLoading?.show()
    }

    override fun hideLoading() {
        view?.vgLoading?.hide()
    }

    override fun showError(errorMsg: String) {
        view?.ivError?.visible()
    }

    override fun hideError() {
        view?.ivError?.gone()
    }
}