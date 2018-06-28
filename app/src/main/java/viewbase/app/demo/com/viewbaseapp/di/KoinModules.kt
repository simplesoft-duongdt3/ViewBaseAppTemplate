package viewbase.app.demo.com.viewbaseapp.di

import okhttp3.OkHttpClient
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import viewbase.app.demo.com.viewbaseapp.App
import viewbase.app.demo.com.viewbaseapp.base.executor.AndroidUseCaseExecution
import viewbase.app.demo.com.viewbaseapp.base.resource.AndroidResourceManager
import viewbase.app.demo.com.viewbaseapp.base.resource.ResourceManager
import viewbase.app.demo.com.viewbaseapp.base.util.DoubleTouchPrevent
import viewbase.app.demo.com.viewbaseapp.data.repository.UserRepository
import viewbase.app.demo.com.viewbaseapp.data.repository.UserRepositoryImpl
import viewbase.app.demo.com.viewbaseapp.data.service.ApiService
import viewbase.app.demo.com.viewbaseapp.data.service.database.UserDataService
import viewbase.app.demo.com.viewbaseapp.data.service.database.UserDataServiceImpl
import viewbase.app.demo.com.viewbaseapp.domain.usecase.GetUsersUseCase
import viewbase.app.demo.com.viewbaseapp.domain.usecase.LoginUseCase
import viewbase.app.demo.com.viewbaseapp.domain.usecase.base.UseCaseExecution
import viewbase.app.demo.com.viewbaseapp.presentation.features.demo.detail.ListUserContract
import viewbase.app.demo.com.viewbaseapp.presentation.features.demo.detail.presenter.ListUserPresenter
import viewbase.app.demo.com.viewbaseapp.presentation.features.demo.login.LoginContract
import viewbase.app.demo.com.viewbaseapp.presentation.features.demo.login.LoginResourceProvider
import viewbase.app.demo.com.viewbaseapp.presentation.features.demo.login.presenter.LoginPresenter
import java.util.concurrent.TimeUnit

val appModule: Module = applicationContext {

    bean {
        AndroidUseCaseExecution() as UseCaseExecution
    }

    bean {
        AndroidResourceManager(App.appContext) as ResourceManager
    }

    factory {
        DoubleTouchPrevent()
    }
}

val networkModule: Module = applicationContext {
    fun createApiService(): ApiService {
        val endPoint = "http://google.com/"
        val retrofit: Retrofit = Retrofit.Builder().baseUrl(endPoint)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(get())
                .build()
        return retrofit.create(ApiService::class.java)
    }

    fun createOkClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .retryOnConnectionFailure(false)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .build()
    }

    bean {
        createApiService()
    }

    bean {
        createOkClient()
    }
}

val dataModule: Module = applicationContext {
    bean {
        UserDataServiceImpl() as UserDataService
    }

    bean {
        UserRepositoryImpl(get(), get()) as UserRepository
    }
}

val loginModule: Module = applicationContext {
    bean {
        LoginResourceProvider(get())
    }

    factory {
        LoginUseCase(get(), get())
    }

    factory {
        LoginPresenter(get(), get()) as LoginContract.Presenter
    }
}

val detailModule: Module = applicationContext {
    factory {
        GetUsersUseCase(get(), get())
    }
    factory {
        ListUserPresenter(get()) as ListUserContract.Presenter
    }
}