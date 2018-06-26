package viewbase.app.demo.com.viewbaseapp.presentation.features.demo.login

import viewbase.app.demo.com.viewbaseapp.base.view.ViewSupportLoading
import viewbase.app.demo.com.viewbaseapp.presentation.features.demo.login.model.LoginResultViewModel
import viewbase.app.demo.com.viewbaseapp.presentation.features.demo.login.model.LoginViewModel
import viewbase.app.demo.com.viewbaseapp.presentation.mvp.PresenterMvp
import viewbase.app.demo.com.viewbaseapp.presentation.mvp.ViewMvp

class LoginContract {
    interface View : ViewMvp, ViewSupportLoading {
        fun showError(errorMsg: String)
        fun hideError()
        fun goToHomeScreen(loginResultViewModel: LoginResultViewModel)
    }

    abstract class Presenter : PresenterMvp<View>() {
        abstract fun requestLogin(loginViewModel: LoginViewModel)
    }
}