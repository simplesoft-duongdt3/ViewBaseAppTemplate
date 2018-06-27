package viewbase.app.demo.com.viewbaseapp.data.repository

import io.reactivex.Single
import viewbase.app.demo.com.viewbaseapp.data.entity.UserDb
import viewbase.app.demo.com.viewbaseapp.data.service.ApiService
import viewbase.app.demo.com.viewbaseapp.data.service.database.UserDataService

class UserRepositoryImpl(private val apiService: ApiService, private val userDataService: UserDataService) : UserRepository {
    override fun getUsers(): Single<List<UserDb>> {
        return Single.create<List<UserDb>> { e ->
            e.onSuccess(userDataService.getAll())
        }
    }
}