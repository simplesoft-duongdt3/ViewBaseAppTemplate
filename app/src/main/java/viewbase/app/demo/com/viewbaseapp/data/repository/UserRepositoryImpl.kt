package viewbase.app.demo.com.viewbaseapp.data.repository

import com.github.javafaker.Faker
import io.reactivex.Single
import viewbase.app.demo.com.viewbaseapp.base.exception.AppException
import viewbase.app.demo.com.viewbaseapp.data.entity.UserDb
import viewbase.app.demo.com.viewbaseapp.data.service.ApiService
import viewbase.app.demo.com.viewbaseapp.data.service.database.UserDataService
import java.util.*
import java.util.concurrent.TimeUnit

class UserRepositoryImpl(private val apiService: ApiService, private val userDataService: UserDataService) : UserRepository {
    override fun getUsers(): Single<List<UserDb>> {
        return Single.create<List<UserDb>> { e ->
            val users = mutableListOf<UserDb>()
            val faker = Faker(Locale("vi"))
            for (i in 1..100) {
                val itemModel = UserDb().apply {
                    id = i * 1L
                    name = faker.name().fullName()
                }

                users.add(itemModel)
            }

            val second = Calendar.getInstance().get(Calendar.SECOND)
            if (second % 3 == 0) {
                e.onError(AppException("Random error load user."))
            } else {
                e.onSuccess(users)
            }
        }.delay(2, TimeUnit.SECONDS)

        /*return Single.create<List<UserDb>> { e ->
            e.onSuccess(userDataService.getAll())
        }*/
    }
}