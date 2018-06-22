package viewbase.app.demo.com.viewbaseapp.domain.usecase

import io.reactivex.Single
import viewbase.app.demo.com.viewbaseapp.base.exception.AppException
import viewbase.app.demo.com.viewbaseapp.domain.usecase.base.UseCase
import viewbase.app.demo.com.viewbaseapp.domain.usecase.base.UseCaseExecution
import viewbase.app.demo.com.viewbaseapp.presentation.features.login.LoginResourceProvider

class LoginUseCase(useCaseExecution: UseCaseExecution, private val loginResourceProvider: LoginResourceProvider) : UseCase<LoginUseCase.Input, LoginUseCase.Output>(useCaseExecution) {
    override fun buildUseCaseObservable(input: Input): Single<Output> {
        return Single.create<Output> { e ->
            if (input.email.startsWith("a")) {
                e.onSuccess(Output(100, "Doan Thanh Duong"))
            } else {
                e.onError(AppException(loginResourceProvider.getEmailPasswordWrongErrorMsg()))
            }
        }
    }

    class Input(val email: String, val pass: String)
    class Output(val userId: Long, val userName: String)
}