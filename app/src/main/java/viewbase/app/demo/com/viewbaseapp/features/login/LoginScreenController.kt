package viewbase.app.demo.com.viewbaseapp.features.login

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.RouterTransaction
import kotlinx.android.synthetic.main.screen_login.view.*
import viewbase.app.demo.com.viewbaseapp.R
import viewbase.app.demo.com.viewbaseapp.features.home.HomeScreenController


class LoginScreenController : Controller() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.screen_login, container, false)
        view.btLogin.setOnClickListener {
            onSimpleDemoClick()
        }
        return view
    }

    private fun onSimpleDemoClick() {
        /*router.pushController(RouterTransaction.with(DetailScreenController())
                .popChangeHandler(HorizontalChangeHandler())
                .pushChangeHandler(HorizontalChangeHandler()))*/
        //router.pushController(RouterTransaction.with(DetailScreenController()))
        router.pushController(RouterTransaction.with(HomeScreenController()))
        router.popController(this)
    }
}