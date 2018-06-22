package viewbase.app.demo.com.viewbaseapp.domain.usecase.base

import viewbase.app.demo.com.viewbaseapp.base.exception.AppException

class ViewResponseResultListener<in T>(private val resultListener: ResultListener<T>) : RawResultListener<T>() {
    override fun success(data: T) {
        resultListener.success(data)
    }

    override fun fail(throwable: Throwable) {
        when (throwable) {
            is AppException -> {
                resultListener.fail(throwable.errorMessage)
            }
            else -> {
                resultListener.fail(throwable.message ?: "Common error.")
            }
        }
    }
}