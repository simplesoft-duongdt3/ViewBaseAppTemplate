package viewbase.app.demo.com.viewbaseapp.presentation.features.login

import viewbase.app.demo.com.viewbaseapp.presentation.features.login.model.LoginResultViewModel
import viewbase.app.demo.com.viewbaseapp.presentation.features.login.model.LoginViewModel
import viewbase.app.demo.com.viewbaseapp.presentation.mvp.PresenterMvp
import viewbase.app.demo.com.viewbaseapp.presentation.mvp.ViewMvp

class LoginContract {
    interface View : ViewMvp {
        fun showError(errorMsg: String)
        fun hideError()
        fun showLoading()
        fun hideLoading()
        fun goToHomeScreen(loginResultViewModel: LoginResultViewModel)
    }

    abstract class Presenter : PresenterMvp<View>() {
        abstract fun requestLogin(loginViewModel: LoginViewModel)
    }
}