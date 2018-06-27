package viewbase.app.demo.com.viewbaseapp.data.service

import io.reactivex.Single
import retrofit2.http.GET
import viewbase.app.demo.com.viewbaseapp.data.entity.User

interface ApiService {
    // ping API
    @GET("/users")
    fun getUsers(): Single<List<User>>
}