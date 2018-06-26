package viewbase.app.demo.com.viewbaseapp.base.viewbase.viewcontroller

import android.os.Bundle
import android.view.View
import com.bluelinelabs.conductor.Controller
import org.koin.standalone.KoinComponent
import viewbase.app.demo.com.viewbaseapp.base.viewbase.lifecycleevent.ControllerLogLifecycleListener

abstract class ViewController(bundle: Bundle?) : Controller(bundle), KoinComponent {
    init {
        addLifecycleListener(ControllerLogLifecycleListener(this.javaClass.simpleName))
        addLifecycleListener(object : LifecycleListener() {
            override fun postCreateView(controller: Controller, view: View) {
                super.postCreateView(controller, view)
                initPostCreateView(view)
            }
        })
    }

    abstract fun initPostCreateView(view: View)
}