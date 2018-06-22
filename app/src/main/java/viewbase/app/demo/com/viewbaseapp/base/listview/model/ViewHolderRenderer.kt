package viewbase.app.demo.com.viewbaseapp.base.listview.model

import android.support.annotation.CallSuper
import android.support.annotation.LayoutRes
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import com.github.vivchar.rendererrecyclerviewadapter.binder.ViewBinder
import com.github.vivchar.rendererrecyclerviewadapter.binder.ViewFinder
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import viewbase.app.demo.com.viewbaseapp.base.util.DoubleTouchPrevent

abstract class ViewHolderRenderer<M : ViewModel> : ViewBinder.Binder<M>, KoinComponent {
    private val doubleTouchPrevent: DoubleTouchPrevent by inject()

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getModelClass(): Class<M>

    abstract fun bindView(model: M, viewFinder: ViewFinder)
    @CallSuper
    override fun bindView(model: M, viewFinder: ViewFinder, payloads: MutableList<Any>) {
        bindView(model, viewFinder)
    }

    fun createViewBinder(): ViewBinder<M> = ViewBinder<M>(getLayoutId(), getModelClass(), this)

    fun runWithCheckMultiTouch(actionKey: String, event: () -> Unit) {
        if (doubleTouchPrevent.check(actionKey)) {
            event.invoke()
        }
    }
}