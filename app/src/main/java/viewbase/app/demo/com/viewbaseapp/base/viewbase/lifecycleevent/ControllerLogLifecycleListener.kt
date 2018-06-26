package viewbase.app.demo.com.viewbaseapp.base.viewbase.lifecycleevent

import android.content.Context
import android.os.Bundle
import android.view.View
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.ControllerChangeHandler
import com.bluelinelabs.conductor.ControllerChangeType
import timber.log.Timber

class ControllerLogLifecycleListener(private val controllerName: String) : Controller.LifecycleListener() {

    override fun postContextUnavailable(controller: Controller) {
        super.postContextUnavailable(controller)
        Timber.d("$controllerName postContextUnavailable")
    }

    override fun postCreateView(controller: Controller, view: View) {
        super.postCreateView(controller, view)
        Timber.d("$controllerName postCreateView")
    }

    override fun postDestroyView(controller: Controller) {
        super.postDestroyView(controller)
        Timber.d("$controllerName postDestroyView")
    }

    override fun onRestoreInstanceState(controller: Controller, savedInstanceState: Bundle) {
        super.onRestoreInstanceState(controller, savedInstanceState)
        Timber.d("$controllerName onRestoreInstanceState")
    }

    override fun preContextUnavailable(controller: Controller, context: Context) {
        super.preContextUnavailable(controller, context)
        Timber.d("$controllerName preContextUnavailable")
    }

    override fun onRestoreViewState(controller: Controller, savedViewState: Bundle) {
        super.onRestoreViewState(controller, savedViewState)
        Timber.d("$controllerName onRestoreViewState")
    }

    override fun preDetach(controller: Controller, view: View) {
        super.preDetach(controller, view)
        Timber.d("$controllerName preDetach")
    }

    override fun onSaveInstanceState(controller: Controller, outState: Bundle) {
        super.onSaveInstanceState(controller, outState)
        Timber.d("$controllerName onSaveInstanceState")
    }

    override fun preCreateView(controller: Controller) {
        super.preCreateView(controller)
        Timber.d("$controllerName preCreateView")
    }

    override fun onSaveViewState(controller: Controller, outState: Bundle) {
        super.onSaveViewState(controller, outState)
        Timber.d("$controllerName onSaveViewState")
    }

    override fun onChangeEnd(controller: Controller, changeHandler: ControllerChangeHandler, changeType: ControllerChangeType) {
        super.onChangeEnd(controller, changeHandler, changeType)
        Timber.d("$controllerName onChangeEnd")
    }

    override fun postDetach(controller: Controller, view: View) {
        super.postDetach(controller, view)
        Timber.d("$controllerName postDetach")
    }

    override fun preAttach(controller: Controller, view: View) {
        super.preAttach(controller, view)
        Timber.d("$controllerName preAttach")
    }

    override fun preContextAvailable(controller: Controller) {
        super.preContextAvailable(controller)
        Timber.d("$controllerName preContextAvailable")
    }

    override fun preDestroy(controller: Controller) {
        super.preDestroy(controller)
        Timber.d("$controllerName preDestroy")
    }

    override fun postContextAvailable(controller: Controller, context: Context) {
        super.postContextAvailable(controller, context)
        Timber.d("$controllerName postContextAvailable")
    }

    override fun onChangeStart(controller: Controller, changeHandler: ControllerChangeHandler, changeType: ControllerChangeType) {
        super.onChangeStart(controller, changeHandler, changeType)
        Timber.d("$controllerName onChangeStart")
    }

    override fun postAttach(controller: Controller, view: View) {
        super.postAttach(controller, view)
        Timber.d("$controllerName postAttach")
    }

    override fun postDestroy(controller: Controller) {
        super.postDestroy(controller)
        Timber.d("$controllerName postDestroy")
    }

    override fun preDestroyView(controller: Controller, view: View) {
        super.preDestroyView(controller, view)
        Timber.d("$controllerName preDestroyView")
    }
}