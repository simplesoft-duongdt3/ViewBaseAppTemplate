package viewbase.app.demo.com.viewbaseapp.base.viewbase.viewcontroller

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.os.Bundle
import com.bluelinelabs.conductor.archlifecycle.ControllerLifecycleRegistryOwner
import org.koin.standalone.KoinComponent

abstract class LifeCycleViewController(bundle: Bundle?) : ViewController(bundle), LifecycleRegistryOwner, KoinComponent {

    private val lifecycleRegistryOwner = ControllerLifecycleRegistryOwner(this)

    override fun getLifecycle(): LifecycleRegistry {
        return lifecycleRegistryOwner.lifecycle
    }

    fun addLifecycleObserver(lifecycleObserver: LifecycleObserver) {
        lifecycle.addObserver(lifecycleObserver)
    }

}