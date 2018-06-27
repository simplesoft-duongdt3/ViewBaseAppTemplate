package viewbase.app.demo.com.viewbaseapp.domain.usecase

import io.reactivex.Single
import viewbase.app.demo.com.viewbaseapp.data.entity.UserDb
import viewbase.app.demo.com.viewbaseapp.data.repository.UserRepository
import viewbase.app.demo.com.viewbaseapp.domain.mapper.UserDbMapper
import viewbase.app.demo.com.viewbaseapp.domain.model.UserModel
import viewbase.app.demo.com.viewbaseapp.domain.usecase.base.UseCaseExecution
import viewbase.app.demo.com.viewbaseapp.domain.usecase.base.UseCaseOutput

class GetUsersUseCase(useCaseExecution: UseCaseExecution, private val userRepository: UserRepository) : UseCaseOutput<GetUsersUseCase.Output>(useCaseExecution) {
    override fun buildUseCaseObservable(): Single<Output> {
        return userRepository.getUsers().map({ users -> createOutput(users) })
    }

    private fun createOutput(users: List<UserDb>): Output {
        val mapList = UserDbMapper().mapList(users)
        return Output(mapList)
    }

    class Output(val users: List<UserModel>)
}