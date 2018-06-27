package viewbase.app.demo.com.viewbaseapp.data.repository

import io.reactivex.Single
import viewbase.app.demo.com.viewbaseapp.data.entity.UserDb

interface UserRepository {
    fun getUsers(): Single<List<UserDb>>
}