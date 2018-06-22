package viewbase.app.demo.com.viewbaseapp.base.executor

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

class AndroidPostTaskSchedulerProvider : SchedulerProvider {
    override fun createScheduler(): Scheduler = AndroidSchedulers.mainThread()
}