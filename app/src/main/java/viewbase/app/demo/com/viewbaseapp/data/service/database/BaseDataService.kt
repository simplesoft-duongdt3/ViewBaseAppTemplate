package viewbase.app.demo.com.viewbaseapp.data.service.database

interface BaseDataService<T> {

    fun getAll(): List<T>

    fun insert(data: T)

    fun insert(listData: List<T>)

    fun update(data: T)

    fun update(listData: List<T>)

    fun save(data: T)

    fun save(listData: List<T>)

    fun deleteAll()

    fun delete(data: T)

    fun delete(listData: List<T>)
}
