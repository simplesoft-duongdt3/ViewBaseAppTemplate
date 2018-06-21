package viewbase.app.demo.com.viewbaseapp.features.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.RouterTransaction
import kotlinx.android.synthetic.main.screen_feature_detail.view.*
import viewbase.app.demo.com.viewbaseapp.R


class DetailScreenController : Controller() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.screen_feature_detail, container, false)
        onViewBound(view)
        return view
    }

    private fun onViewBound(view: View) {
        getChildRouter(view.vgHeader).setRoot(RouterTransaction.with(HeaderController()))
        getChildRouter(view.vgListItem).setRoot(RouterTransaction.with(ListItemController()))
    }

    override fun onDestroyView(view: View) {
        super.onDestroyView(view)
    }
}