package viewbase.app.demo.com.viewbaseapp.domain.usecase.base

import io.reactivex.disposables.Disposable

class UseCaseTask(var disposable: Disposable) {

    fun cancel() {
        disposable.dispose()
    }
}