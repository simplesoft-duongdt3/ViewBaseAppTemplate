package viewbase.app.demo.com.viewbaseapp

import android.support.multidex.MultiDexApplication
import android.support.v7.app.AppCompatDelegate
import org.koin.android.ext.android.startKoin
import timber.log.Timber
import viewbase.app.demo.com.viewbaseapp.di.appModule
import viewbase.app.demo.com.viewbaseapp.di.loginModule

class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        appContext = this
        initLogger()
        initDI()
    }

    private fun initLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun initDI() {
        startKoin(this, listOf(appModule, loginModule))
    }

    companion object {
        init {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        }

        @JvmStatic
        lateinit var appContext: App
            private set
    }
}