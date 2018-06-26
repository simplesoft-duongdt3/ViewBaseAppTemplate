package viewbase.app.demo.com.viewbaseapp.presentation.features.demo.detail.view

import com.github.vivchar.rendererrecyclerviewadapter.binder.ViewFinder
import viewbase.app.demo.com.viewbaseapp.R
import viewbase.app.demo.com.viewbaseapp.base.listview.model.ViewHolderRenderer
import viewbase.app.demo.com.viewbaseapp.presentation.features.demo.detail.model.UserViewHolderModel

class UserViewHolderRenderer : ViewHolderRenderer<UserViewHolderModel>() {
    override fun getLayoutId(): Int {
        return R.layout.item_view_holder
    }

    override fun getModelClass(): Class<UserViewHolderModel> {
        return UserViewHolderModel::class.java
    }

    override fun bindView(model: UserViewHolderModel, viewFinder: ViewFinder) {
        viewFinder.setText(R.id.tvItem, model.name)
    }
}