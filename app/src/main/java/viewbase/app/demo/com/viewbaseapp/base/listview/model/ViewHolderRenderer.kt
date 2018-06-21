package viewbase.app.demo.com.viewbaseapp.base.listview.model

import android.support.annotation.CallSuper
import android.support.annotation.LayoutRes
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import com.github.vivchar.rendererrecyclerviewadapter.binder.ViewBinder
import com.github.vivchar.rendererrecyclerviewadapter.binder.ViewFinder
import viewbase.app.demo.com.viewbaseapp.base.util.DoubleTouchPrevent
import viewbase.app.demo.com.viewbaseapp.base.util.DoubleTouchPreventProvider

abstract class ViewHolderRenderer<M : ViewModel> : ViewBinder.Binder<M> {
    private val doubleTouchPrevent: DoubleTouchPrevent = DoubleTouchPreventProvider.createDoubleTouchPrevent()
    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getModelClass(): Class<M>

    abstract fun bindView(model: M, viewRoot: ViewFinder)
    @CallSuper
    override fun bindView(model: M, finder: ViewFinder, payloads: MutableList<Any>) {
        bindView(model, finder)
    }

    fun createViewBinder(): ViewBinder<M> = ViewBinder<M>(getLayoutId(), getModelClass(), this)

    fun runWithCheckMultiTouch(actionKey: String, event: () -> Unit) {
        if (doubleTouchPrevent.check(actionKey)) {
            event.invoke()
        }
    }
}