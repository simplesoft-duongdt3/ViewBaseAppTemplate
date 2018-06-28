package viewbase.app.demo.com.viewbaseapp.presentation.features.demo.detail

import viewbase.app.demo.com.viewbaseapp.base.view.ViewSupportError
import viewbase.app.demo.com.viewbaseapp.base.view.ViewSupportLoading
import viewbase.app.demo.com.viewbaseapp.presentation.features.demo.detail.model.UserViewHolderModel
import viewbase.app.demo.com.viewbaseapp.presentation.mvp.PresenterMvp
import viewbase.app.demo.com.viewbaseapp.presentation.mvp.ViewMvp

class ListUserContract {
    interface View : ViewMvp, ViewSupportLoading, ViewSupportError {
        fun renderItems(userViewHolderModels: List<UserViewHolderModel>)
    }

    abstract class Presenter : PresenterMvp<View>() {
        abstract fun loadItems()
    }
}