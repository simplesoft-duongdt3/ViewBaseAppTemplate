package viewbase.app.demo.com.viewbaseapp.data.entity

import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.structure.BaseModel
import viewbase.app.demo.com.viewbaseapp.data.database.AppDatabase

@Table(database = AppDatabase::class, allFields = true)
class UserDb : BaseModel() {
    @PrimaryKey(autoincrement = true)
    var id: Long = 0
    var name: String? = null
}
