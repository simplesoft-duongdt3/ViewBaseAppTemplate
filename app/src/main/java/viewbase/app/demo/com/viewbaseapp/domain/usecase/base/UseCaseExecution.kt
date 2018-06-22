package viewbase.app.demo.com.viewbaseapp.domain.usecase.base

import io.reactivex.Scheduler

open class UseCaseExecution constructor(val execution: Scheduler, val postExecution: Scheduler)