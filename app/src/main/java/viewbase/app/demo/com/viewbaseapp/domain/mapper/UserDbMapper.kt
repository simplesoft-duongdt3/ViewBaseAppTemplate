package viewbase.app.demo.com.viewbaseapp.domain.mapper

import viewbase.app.demo.com.viewbaseapp.base.mapper.Mapper
import viewbase.app.demo.com.viewbaseapp.data.entity.UserDb
import viewbase.app.demo.com.viewbaseapp.domain.model.UserModel

class UserDbMapper : Mapper<UserDb, UserModel>() {
    override fun map(input: UserDb): UserModel {
        return UserModel(input.name)
    }
}