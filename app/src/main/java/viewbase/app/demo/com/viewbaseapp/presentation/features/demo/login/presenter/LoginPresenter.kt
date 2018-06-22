package viewbase.app.demo.com.viewbaseapp.presentation.features.demo.login.presenter

import viewbase.app.demo.com.viewbaseapp.domain.usecase.LoginUseCase
import viewbase.app.demo.com.viewbaseapp.domain.usecase.base.ResultListener
import viewbase.app.demo.com.viewbaseapp.presentation.features.demo.login.LoginContract
import viewbase.app.demo.com.viewbaseapp.presentation.features.demo.login.LoginResourceProvider
import viewbase.app.demo.com.viewbaseapp.presentation.features.demo.login.model.LoginResultViewModel
import viewbase.app.demo.com.viewbaseapp.presentation.features.demo.login.model.LoginViewModel

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
            requestLoginUseCase(loginViewModel)
        }
    }

    private fun requestLoginUseCase(loginViewModel: LoginViewModel) {
        loginUseCase.cancel()
        val input = LoginUseCase.Input(loginViewModel.email, loginViewModel.pass)
        loginUseCase.executeAsync(object : ResultListener<LoginUseCase.Output> {
            override fun success(data: LoginUseCase.Output) {
                view?.let { view ->
                    view.hideError()
                    view.hideLoading()
                    view.goToHomeScreen(LoginResultViewModel(data.userId, data.userName))
                }
            }

            override fun fail(msgError: String) {
                view?.let { view ->
                    view.hideLoading()
                    view.showError(msgError)
                }
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