package viewbase.app.demo.com.viewbaseapp.presentation.mvp

import java.lang.ref.WeakReference

abstract class PresenterMvp<V : ViewMvp> {
    private var weakReference: WeakReference<V>? = null
    fun attachView(view: V) {
        if (!isViewAttached) {
            weakReference = WeakReference(view)
        }
    }

    fun detachView() {
        weakReference?.clear()
        weakReference = null

        onDetachView()
    }

    protected open fun onDetachView() {

    }

    val view: V?
        get() = weakReference?.get()

    private val isViewAttached: Boolean
        get() = weakReference != null && weakReference!!.get() != null
}