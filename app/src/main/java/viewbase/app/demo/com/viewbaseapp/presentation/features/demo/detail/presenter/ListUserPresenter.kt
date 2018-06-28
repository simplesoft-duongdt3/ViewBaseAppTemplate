package viewbase.app.demo.com.viewbaseapp.presentation.features.demo.detail.presenter

import viewbase.app.demo.com.viewbaseapp.domain.mapper.UserMapper
import viewbase.app.demo.com.viewbaseapp.domain.usecase.GetUsersUseCase
import viewbase.app.demo.com.viewbaseapp.domain.usecase.base.ResultListener
import viewbase.app.demo.com.viewbaseapp.presentation.features.demo.detail.ListUserContract

class ListUserPresenter(private val getUsersUseCase: GetUsersUseCase) : ListUserContract.Presenter() {
    override fun loadItems() {
        view?.hideError()
        view?.showLoading()

        getUsersUseCase.executeAsync(object : ResultListener<GetUsersUseCase.Output> {
            override fun success(data: GetUsersUseCase.Output) {
                val userViewHolderModels = UserMapper().mapList(data.users)
                view?.renderItems(userViewHolderModels)
                view?.hideLoading()
            }

            override fun fail(msgError: String) {
                view?.renderItems(listOf())
                view?.hideLoading()
                view?.showError(msgError)
            }
        })
    }
}