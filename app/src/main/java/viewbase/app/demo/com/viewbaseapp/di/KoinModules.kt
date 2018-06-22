package viewbase.app.demo.com.viewbaseapp.di

import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext
import viewbase.app.demo.com.viewbaseapp.App
import viewbase.app.demo.com.viewbaseapp.base.executor.AndroidUseCaseExecution
import viewbase.app.demo.com.viewbaseapp.base.resource.AndroidResourceManager
import viewbase.app.demo.com.viewbaseapp.base.util.DoubleTouchPrevent
import viewbase.app.demo.com.viewbaseapp.domain.usecase.LoginUseCase
import viewbase.app.demo.com.viewbaseapp.domain.usecase.base.UseCaseExecution
import viewbase.app.demo.com.viewbaseapp.presentation.features.demo.login.LoginContract
import viewbase.app.demo.com.viewbaseapp.presentation.features.demo.login.LoginResourceProvider
import viewbase.app.demo.com.viewbaseapp.presentation.features.demo.login.presenter.LoginPresenter

val appModule: Module = applicationContext {
    bean {
        AndroidUseCaseExecution() as UseCaseExecution
    }

    bean {
        AndroidResourceManager(App.appContext)
    }

    factory {
        DoubleTouchPrevent()
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