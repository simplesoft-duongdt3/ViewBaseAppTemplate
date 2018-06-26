package viewbase.app.demo.com.viewbaseapp.base.mapper

abstract class Mapper<in I, out O> {
    abstract fun map(input: I): O

    fun mapList(inputs: List<I>): List<O> = inputs.map { input -> map(input) }
}