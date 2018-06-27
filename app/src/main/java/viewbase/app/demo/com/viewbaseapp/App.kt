package viewbase.app.demo.com.viewbaseapp

import android.support.multidex.MultiDexApplication
import android.support.v7.app.AppCompatDelegate
import com.raizlabs.android.dbflow.config.DatabaseConfig
import com.raizlabs.android.dbflow.config.FlowConfig
import com.raizlabs.android.dbflow.config.FlowManager
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.Module
import timber.log.Timber
import viewbase.app.demo.com.viewbaseapp.data.database.AppDatabase
import viewbase.app.demo.com.viewbaseapp.di.*

class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        appContext = this
        initLogger()
        initDatabase()
        initDI()
    }

    private fun initDatabase() {
        FlowManager.init(FlowConfig.builder(this)
                .addDatabaseConfig(DatabaseConfig.builder(AppDatabase.javaClass)
                    .databaseName(AppDatabase.NAME)
                    .build())
                .build())
    }

    private fun initLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun initDI() {
        val globalModules = listOf(appModule, networkModule, dataModule)
        val featureModules = listOf(loginModule, detailModule)

        startKoin(this, mutableListOf<Module>().apply {
            addAll(globalModules)
            addAll(featureModules)
        })
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