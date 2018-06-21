package viewbase.app.demo.com.viewbaseapp.base.kotlinex.number

import viewbase.app.demo.com.viewbaseapp.base.util.DecimalUtils
import java.util.*

/**
 * Created by leducanh on 21/12/2017.
 */

// Double

fun Double.formatNumber(): String {
    return DecimalUtils.decimalFormat(this, 2, Locale.ENGLISH, DecimalUtils.Companion.GroupDecimalMode.COMMA, DecimalUtils.Companion.AfterDotMode.IF_EXIST)
}

fun Double.roundUp(numberDigit: Int): Double {
    return DecimalUtils.roundNumber(this, numberDigit, DecimalUtils.Companion.RoundMode.UP)
}

fun Double.roundDown(numberDigit: Int): Double {
    return DecimalUtils.roundNumber(this, numberDigit, DecimalUtils.Companion.RoundMode.DOWN)
}

fun Double.roundNatural(numberDigit: Int): Double {
    return DecimalUtils.roundNumber(this, numberDigit, DecimalUtils.Companion.RoundMode.NATURAL)
}
// Long

fun Long.formatTimeHHMM(): String {
    val second: Long = this / 1000
    var minute: Long = second / 60
    val hour: Long = minute / 60
    minute %= 60
    return String.format(Locale.ENGLISH, "%02d:%02d", hour, minute)
}

// Int
fun Int.formatNumber(): String {
    return DecimalUtils.decimalFormat(this.toDouble(), 0, Locale.ENGLISH, DecimalUtils.Companion.GroupDecimalMode.COMMA, DecimalUtils.Companion.AfterDotMode.IF_EXIST)
}