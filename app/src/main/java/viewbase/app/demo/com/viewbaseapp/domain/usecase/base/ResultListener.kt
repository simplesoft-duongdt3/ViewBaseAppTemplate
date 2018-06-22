package viewbase.app.demo.com.viewbaseapp.domain.usecase.base

interface ResultListener<in T> {
    fun success(data: T)
    fun fail(msgError: String)
}