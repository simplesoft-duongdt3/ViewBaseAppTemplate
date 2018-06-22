package viewbase.app.demo.com.viewbaseapp.base.executor

import viewbase.app.demo.com.viewbaseapp.domain.usecase.base.UseCaseExecution

class AndroidUseCaseExecution : UseCaseExecution(AndroidTaskSchedulerProvider().createScheduler(), AndroidPostTaskSchedulerProvider().createScheduler())