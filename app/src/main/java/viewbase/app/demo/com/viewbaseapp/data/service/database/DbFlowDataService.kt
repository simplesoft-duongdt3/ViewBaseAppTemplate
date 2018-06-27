package viewbase.app.demo.com.viewbaseapp.data.service.database

import com.raizlabs.android.dbflow.config.FlowManager
import com.raizlabs.android.dbflow.sql.language.SQLite
import com.raizlabs.android.dbflow.structure.Model
import com.raizlabs.android.dbflow.structure.database.transaction.FastStoreModelTransaction
import com.raizlabs.android.dbflow.structure.database.transaction.ProcessModelTransaction
import viewbase.app.demo.com.viewbaseapp.data.database.AppDatabase


abstract class DbFlowDataService<T : Model> : BaseDataService<T> {
    abstract fun getClassModel(): Class<T>

    override fun insert(data: T) {
        data.insert()
    }

    override fun insert(listData: List<T>) {
        val storeModelTransaction = FastStoreModelTransaction
                .insertBuilder(FlowManager.getModelAdapter(getClassModel()))
                .addAll(listData)
                .build()

        AppDatabase.executeTransaction(storeModelTransaction)
    }

    override fun update(data: T) {
        data.update()
    }

    override fun update(listData: List<T>) {
        val storeModelTransaction = FastStoreModelTransaction
                .updateBuilder(FlowManager.getModelAdapter(getClassModel()))
                .addAll(listData)
                .build()

        AppDatabase.executeTransaction(storeModelTransaction)
    }

    override fun save(data: T) {
        data.save()
    }

    override fun save(listData: List<T>) {
        val storeModelTransaction = FastStoreModelTransaction
                .saveBuilder(FlowManager.getModelAdapter(getClassModel()))
                .addAll(listData)
                .build()

        AppDatabase.executeTransaction(storeModelTransaction)
    }

    override fun getAll(): List<T> {
        return SQLite.select().from(getClassModel()).queryList()
    }

    override fun deleteAll() {
        SQLite.delete(getClassModel()).execute()
    }

    override fun delete(data: T) {
        data.delete()
    }

    override fun delete(listData: List<T>) {
        val processModelTransaction = ProcessModelTransaction.Builder<T>(ProcessModelTransaction.ProcessModel<T> { obj, wrapper -> obj.delete(wrapper) }).addAll(listData).build()
        AppDatabase.executeTransaction(processModelTransaction)
    }
}
