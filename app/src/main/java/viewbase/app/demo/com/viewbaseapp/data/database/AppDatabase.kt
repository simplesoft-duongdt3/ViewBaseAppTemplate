package viewbase.app.demo.com.viewbaseapp.data.database

import com.raizlabs.android.dbflow.annotation.Database
import com.raizlabs.android.dbflow.config.FlowManager
import com.raizlabs.android.dbflow.config.FlowManager.getDatabase
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction
import java.io.File

@Database(version = AppDatabase.VERSION)
object AppDatabase {
    const val VERSION = 1
    const val NAME = "AppDatabase"

    fun executeTransaction(iTransaction: ITransaction) {
        val databaseDefinition = getDatabase(AppDatabase.NAME)
        databaseDefinition.executeTransaction(iTransaction)
    }

    fun getDatabaseFile(): File {
        return FlowManager.getContext().getDatabasePath(AppDatabase.NAME)
    }
}