package viewbase.app.demo.com.viewbaseapp.base.executor

import io.reactivex.Scheduler

interface SchedulerProvider {
    fun createScheduler(): Scheduler
}