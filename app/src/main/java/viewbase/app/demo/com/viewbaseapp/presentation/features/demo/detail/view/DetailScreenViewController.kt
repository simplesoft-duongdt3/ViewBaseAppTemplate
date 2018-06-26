package viewbase.app.demo.com.viewbaseapp.presentation.features.demo.detail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.RouterTransaction
import kotlinx.android.synthetic.main.screen_feature_detail.view.*
import viewbase.app.demo.com.viewbaseapp.R
import viewbase.app.demo.com.viewbaseapp.base.viewbase.viewcontroller.ViewController


class DetailScreenViewController(bundle: Bundle?) : ViewController(bundle) {

    constructor() : this(null)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.screen_feature_detail, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View) {
        getChildRouter(view.vgHeader).setRoot(RouterTransaction.with(HeaderViewController()))
        getChildRouter(view.vgListItem).setRoot(RouterTransaction.with(ListItemViewController()))
    }

    override fun onDestroyView(view: View) {
        super.onDestroyView(view)
    }
}