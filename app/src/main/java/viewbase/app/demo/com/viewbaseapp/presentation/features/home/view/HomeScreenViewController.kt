package viewbase.app.demo.com.viewbaseapp.presentation.features.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.RouterTransaction
import kotlinx.android.synthetic.main.screen_feature_home.view.*
import viewbase.app.demo.com.viewbaseapp.R
import viewbase.app.demo.com.viewbaseapp.base.extra.BundleExtraObject
import viewbase.app.demo.com.viewbaseapp.base.extra.BundleOptionsCompanion
import viewbase.app.demo.com.viewbaseapp.base.viewbase.ViewController
import viewbase.app.demo.com.viewbaseapp.presentation.features.detail.view.DetailScreenViewController
import viewbase.app.demo.com.viewbaseapp.presentation.features.login.model.LoginResultViewModel
import viewbase.app.demo.com.viewbaseapp.presentation.features.pager.view.PagerScreenViewController

class HomeScreenViewController(bundle: Bundle?) : ViewController(bundle) {
    object BundleOptions {
        var Bundle.loginResultExtra by BundleExtraObject<LoginResultViewModel>("loginResultExtra")

        fun create(loginResultViewModel: LoginResultViewModel) = Bundle().apply {
            loginResultExtra = loginResultViewModel
        }
    }

    companion object : BundleOptionsCompanion<BundleOptions>(BundleOptions)

    private var loginResultExtra : LoginResultViewModel? = null

    init {
        bundle?.options { options ->
            loginResultExtra = options.loginResultExtra
        }
    }

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

        view.tvTittle.text = loginResultExtra?.userName
    }

    private fun onMenuSelected(menuAction : MenuAction) {
        if (menuAction == MenuAction.DETAIL) {
            getChildRouter(vgContent).pushController(RouterTransaction.with(DetailScreenViewController()))
        } else if (menuAction == MenuAction.PAGER) {
            getChildRouter(vgContent).pushController(RouterTransaction.with(PagerScreenViewController()))
        }
    }
}