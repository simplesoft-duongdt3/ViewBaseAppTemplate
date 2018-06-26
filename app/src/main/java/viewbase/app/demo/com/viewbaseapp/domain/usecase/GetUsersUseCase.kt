package viewbase.app.demo.com.viewbaseapp.domain.usecase

import com.github.javafaker.Faker
import io.reactivex.Single
import viewbase.app.demo.com.viewbaseapp.base.exception.AppException
import viewbase.app.demo.com.viewbaseapp.domain.model.UserModel
import viewbase.app.demo.com.viewbaseapp.domain.usecase.base.UseCaseExecution
import viewbase.app.demo.com.viewbaseapp.domain.usecase.base.UseCaseOutput
import java.util.*
import java.util.concurrent.TimeUnit

class GetUsersUseCase(useCaseExecution: UseCaseExecution) : UseCaseOutput<GetUsersUseCase.Output>(useCaseExecution) {
    override fun buildUseCaseObservable(): Single<Output> {
        return Single.create<Output> { e ->
            val users = mutableListOf<UserModel>()
            val faker = Faker(Locale("vi"))
            for (i in 1..100) {
                val itemModel = UserModel(faker.name().fullName())
                users.add(itemModel)
            }

            val second = Calendar.getInstance().get(Calendar.SECOND)
            if (second % 3 == 0) {
                e.onError(AppException("Random error load user."))
            } else {
                e.onSuccess(Output(users))
            }
        }.delay(2, TimeUnit.SECONDS)
    }

    class Output(val users: List<UserModel>)
}