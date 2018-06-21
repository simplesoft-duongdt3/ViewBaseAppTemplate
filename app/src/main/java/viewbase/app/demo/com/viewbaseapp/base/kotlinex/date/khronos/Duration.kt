package viewbase.app.demo.com.viewbaseapp.base.kotlinex.date.khronos

import java.util.*

class Duration(internal val unit: Int, internal val value: Int) {
    val ago = calculate(from = Date(), value = -value)

    val since = calculate(from = Date(), value = value)

    private fun calculate(from: Date, value: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.time = from
        calendar.add(unit, value)
        return calendar.time
    }

    override fun hashCode() = unit.hashCode() * value.hashCode()

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is Duration) {
            return false
        }
        return unit == other.unit && value == other.value
    }
}