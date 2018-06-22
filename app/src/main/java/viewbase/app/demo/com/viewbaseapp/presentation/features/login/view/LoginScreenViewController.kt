package viewbase.app.demo.com.viewbaseapp.presentation.features.login.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.RouterTransaction
import kotlinx.android.synthetic.main.screen_login.view.*
import viewbase.app.demo.com.viewbaseapp.R
import viewbase.app.demo.com.viewbaseapp.base.extra.BundleOptionsCompanion
import viewbase.app.demo.com.viewbaseapp.base.kotlinex.view.gone
import viewbase.app.demo.com.viewbaseapp.base.kotlinex.view.visible
import viewbase.app.demo.com.viewbaseapp.base.viewbase.ViewController
import viewbase.app.demo.com.viewbaseapp.presentation.features.home.view.HomeScreenViewController
import viewbase.app.demo.com.viewbaseapp.presentation.features.login.LoginContract
import viewbase.app.demo.com.viewbaseapp.presentation.features.login.model.LoginResultViewModel
import viewbase.app.demo.com.viewbaseapp.presentation.features.login.model.LoginViewModel
import viewbase.app.demo.com.viewbaseapp.presentation.features.login.presenter.LoginPresenter

class LoginScreenViewController(bundle: Bundle?) : ViewController(bundle), LoginContract.View {
    constructor() : this(null)

    private val presenter: LoginContract.Presenter = LoginPresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.screen_login, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View) {
        presenter.attachView(this)
        view.btLogin.setOnClickListener {
            val loginViewModel = LoginViewModel(view.etEmail.text.toString(), view.etPass.text.toString())
            presenter.requestLogin(loginViewModel)
        }
    }

    override fun onDestroyView(view: View) {
        super.onDestroyView(view)
        presenter.detachView()
    }

    override fun showError(errorMsg: String) {
        view?.tvError?.text = errorMsg
    }

    override fun hideError() {
        view?.tvError?.text = ""
    }

    override fun showLoading() {
        view?.vgLoading?.visible()
    }

    override fun hideLoading() {
        view?.vgLoading?.gone()
    }

    override fun goToHomeScreen(loginResultViewModel: LoginResultViewModel) {
        val bundle = HomeScreenViewController.BundleOptions.create(loginResultViewModel)
        router.pushController(RouterTransaction.with(HomeScreenViewController(bundle)))
        router.popController(this)

        /*router.pushController(RouterTransaction.with(DetailScreenController())
                .popChangeHandler(HorizontalChangeHandler())
                .pushChangeHandler(HorizontalChangeHandler()))*/
        //router.pushController(RouterTransaction.with(DetailScreenController()))
    }
}