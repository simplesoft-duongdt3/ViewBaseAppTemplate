package viewbase.app.demo.com.viewbaseapp.base.executor

import io.reactivex.Scheduler

class ExecutionThread(schedulerProvider: SchedulerProvider) {
    val scheduler: Scheduler = schedulerProvider.createScheduler()
}
