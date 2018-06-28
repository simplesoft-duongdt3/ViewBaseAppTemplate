package viewbase.app.demo.com.viewbaseapp.presentation.features.demo.login.view

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.RouterTransaction
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.screen_login.view.*
import org.koin.standalone.inject
import timber.log.Timber
import viewbase.app.demo.com.viewbaseapp.R
import viewbase.app.demo.com.viewbaseapp.base.kotlinex.view.hideKeyboard
import viewbase.app.demo.com.viewbaseapp.base.util.DoubleTouchPrevent
import viewbase.app.demo.com.viewbaseapp.base.viewbase.screenchangehandler.VerticalChangeHandler
import viewbase.app.demo.com.viewbaseapp.base.viewbase.viewcontroller.ViewController
import viewbase.app.demo.com.viewbaseapp.presentation.features.demo.home.view.HomeScreenViewController
import viewbase.app.demo.com.viewbaseapp.presentation.features.demo.login.LoginContract
import viewbase.app.demo.com.viewbaseapp.presentation.features.demo.login.model.LoginResultViewModel
import viewbase.app.demo.com.viewbaseapp.presentation.features.demo.login.model.LoginViewModel


class LoginScreenViewController(bundle: Bundle?) : ViewController(bundle), LoginContract.View {
    constructor() : this(null)

    private val doubleTouchPrevent: DoubleTouchPrevent by inject()

    private val presenter: LoginContract.Presenter by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        return inflater.inflate(R.layout.screen_login, container, false)
    }

    override fun initPostCreateView(view: View) {
        initView(view)
    }

    private fun initView(view: View) {
        presenter.attachView(this)
        view.btLogin.setOnClickListener {
            view.hideKeyboard()
            if (doubleTouchPrevent.check("LoginScreen_login_click")) {
                val rxPermissions = RxPermissions(activity!!)
                rxPermissions.requestEach(Manifest.permission.CAMERA)
                        .subscribe { granted ->
                            when {
                                granted.granted -> {
                                    Timber.d("granted camera")
                                    requestLogin(view)
                                }
                                granted.shouldShowRequestPermissionRationale -> {
                                    Timber.d("not granted camera shouldShowRequestPermissionRationale")
                                }
                                else -> Timber.d("not granted camera")
                            }
                        }
            }
        }
    }

    private fun requestLogin(view: View) {
        val loginViewModel = LoginViewModel(view.etEmail.text.toString(), view.etPass.text.toString())
        presenter.requestLogin(loginViewModel)
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
        view?.vgLoading?.show()
    }

    override fun hideLoading() {
        view?.vgLoading?.hide()
    }

    override fun goToHomeScreen(loginResultViewModel: LoginResultViewModel) {
        val bundle = HomeScreenViewController.BundleOptions.create(loginResultViewModel)
        router.pushController(RouterTransaction.with(HomeScreenViewController(bundle))
                .popChangeHandler(VerticalChangeHandler())
                .pushChangeHandler(VerticalChangeHandler())
        )
        //router.popController(this)

        /*router.pushController(RouterTransaction.with(DetailScreenController())
                .popChangeHandler(HorizontalChangeHandler())
                .pushChangeHandler(HorizontalChangeHandler()))*/
        //router.pushController(RouterTransaction.with(DetailScreenController()))
    }
}