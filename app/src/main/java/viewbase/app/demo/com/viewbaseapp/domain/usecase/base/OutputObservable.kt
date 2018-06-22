package viewbase.app.demo.com.viewbaseapp.domain.usecase.base

class OutputObservable<O> : DefaultObserver<O>() {
    var output: O? = null
    var exception: Throwable? = null
    override fun onSuccess(data: O) {
        super.onSuccess(data)
        output = data
    }

    override fun onError(throwable: Throwable) {
        super.onError(throwable)
        this.exception = throwable

    }
}
