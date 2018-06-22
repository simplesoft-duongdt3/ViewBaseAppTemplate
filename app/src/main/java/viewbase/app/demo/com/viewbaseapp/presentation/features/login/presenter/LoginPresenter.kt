package viewbase.app.demo.com.viewbaseapp.presentation.features.login.presenter

import viewbase.app.demo.com.viewbaseapp.domain.usecase.LoginUseCase
import viewbase.app.demo.com.viewbaseapp.domain.usecase.base.ResultListener
import viewbase.app.demo.com.viewbaseapp.presentation.features.login.LoginContract
import viewbase.app.demo.com.viewbaseapp.presentation.features.login.LoginResourceProvider
import viewbase.app.demo.com.viewbaseapp.presentation.features.login.model.LoginResultViewModel
import viewbase.app.demo.com.viewbaseapp.presentation.features.login.model.LoginViewModel

class LoginPresenter(private val loginUseCase: LoginUseCase, private val loginResourceProvider: LoginResourceProvider) : LoginContract.Presenter() {
    override fun requestLogin(loginViewModel: LoginViewModel) {
        view?.let { view ->
            processLogin(view, loginViewModel)
        }
    }

    private fun processLogin(view: LoginContract.View, loginViewModel: LoginViewModel) {
        view.hideError()
        view.showLoading()
        val errorMsg: String = validateLogin(loginViewModel)
        if (!errorMsg.isEmpty()) {
            view.showError(errorMsg)
            view.hideLoading()
        } else {
            requestLoginUseCase(loginViewModel, view)
        }
    }

    private fun requestLoginUseCase(loginViewModel: LoginViewModel, view: LoginContract.View) {
        loginUseCase.cancel()
        val input = LoginUseCase.Input(loginViewModel.email, loginViewModel.pass)
        loginUseCase.executeAsync(object : ResultListener<LoginUseCase.Output> {
            override fun success(data: LoginUseCase.Output) {
                view.hideError()
                view.hideLoading()
                view.goToHomeScreen(LoginResultViewModel(data.userId, data.userName))
            }

            override fun fail(msgError: String) {
                view.hideLoading()
                view.showError(msgError)
            }
        }, input)
    }

    private fun validateLogin(loginViewModel: LoginViewModel): String {
        if (!loginViewModel.isEnoughInfo()) {
            return loginResourceProvider.getLoginFailErrorMsg()
        }
        return ""
    }

    override fun onDetachView() {
        super.onDetachView()
        loginUseCase.cancel()
    }
}