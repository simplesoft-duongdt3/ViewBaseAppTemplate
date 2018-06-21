package viewbase.app.demo.com.viewbaseapp.features.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.RouterTransaction
import kotlinx.android.synthetic.main.screen_feature_home.view.*
import viewbase.app.demo.com.viewbaseapp.R
import viewbase.app.demo.com.viewbaseapp.features.detail.DetailScreenController
import viewbase.app.demo.com.viewbaseapp.features.pager.PagerScreenController


class HomeScreenController : Controller() {

    private lateinit var vgContent : ViewGroup
    enum class MenuAction {
        PAGER, DETAIL
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.screen_feature_home, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View) {
        vgContent = view.vgContent
        view.tvDetail.setOnClickListener {
            onMenuSelected(MenuAction.DETAIL)
        }

        view.tvPager.setOnClickListener {
            onMenuSelected(MenuAction.PAGER)
        }

        onMenuSelected(MenuAction.DETAIL)
    }

    private fun onMenuSelected(menuAction : MenuAction) {
        if (menuAction == MenuAction.DETAIL) {
            getChildRouter(vgContent).pushController(RouterTransaction.with(DetailScreenController()))
        } else if (menuAction == MenuAction.PAGER) {
            getChildRouter(vgContent).pushController(RouterTransaction.with(PagerScreenController()))
        }
    }
}