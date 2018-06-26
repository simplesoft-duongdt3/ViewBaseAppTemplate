package viewbase.app.demo.com.viewbaseapp.base.viewbase.viewcontroller

import android.os.Bundle
import com.bluelinelabs.conductor.Controller
import org.koin.standalone.KoinComponent
import viewbase.app.demo.com.viewbaseapp.base.viewbase.lifecycleevent.ControllerLogLifecycleListener

abstract class ViewController(bundle: Bundle?) : Controller(bundle), KoinComponent {
    init {
        addLifecycleListener(ControllerLogLifecycleListener(this.javaClass.simpleName))
    }
}