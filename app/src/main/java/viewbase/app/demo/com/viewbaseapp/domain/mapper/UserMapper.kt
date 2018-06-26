package viewbase.app.demo.com.viewbaseapp.domain.mapper

import viewbase.app.demo.com.viewbaseapp.base.mapper.Mapper
import viewbase.app.demo.com.viewbaseapp.domain.model.UserModel
import viewbase.app.demo.com.viewbaseapp.presentation.features.demo.detail.model.UserViewHolderModel

class UserMapper : Mapper<UserModel, UserViewHolderModel>() {
    override fun map(input: UserModel): UserViewHolderModel {
        return UserViewHolderModel(input.name)
    }
}