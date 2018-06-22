package viewbase.app.demo.com.viewbaseapp.domain.usecase.base

abstract class RawResultListener<in T> {
    abstract fun success(data: T)
    abstract fun fail(throwable: Throwable)
}