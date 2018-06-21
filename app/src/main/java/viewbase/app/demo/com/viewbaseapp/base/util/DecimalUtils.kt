package viewbase.app.demo.com.viewbaseapp.base.util

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

class DecimalUtils {
    companion object {
        enum class GroupDecimalMode {
            DOT,
            COMMA,
            LOCALE
        }

        enum class AfterDotMode {
            ALWAYS_ZERO,
            IF_EXIST
        }

        enum class RoundMode {
            UP,
            DOWN,
            NATURAL;


            companion object {

                //0.3755 3 digit BigDecimal.ROUND_CEILING 0.376
                //0.3755 3 digit BigDecimal.ROUND_FLOOR 0.375
                //0.3755 3 digit BigDecimal.ROUND_HALF_UP 0.376
                fun getRoundMode(roundMode: RoundMode): Int {
                    var mode = BigDecimal.ROUND_HALF_UP
                    when (roundMode) {
                        UP -> mode = BigDecimal.ROUND_CEILING
                        DOWN -> mode = BigDecimal.ROUND_FLOOR
                        NATURAL -> mode = BigDecimal.ROUND_HALF_UP
                    }
                    return mode
                }
            }
        }

        @JvmStatic
        fun decimalFormat(value: Double, numberAfterDot: Int, locale: Locale
                          , groupDecimalMode: GroupDecimalMode, afterDotMode: AfterDotMode): String {
            val symbols = DecimalFormatSymbols(locale)
            if (groupDecimalMode != GroupDecimalMode.LOCALE) {
                if (groupDecimalMode == GroupDecimalMode.DOT) {
                    symbols.groupingSeparator = '.'
                    symbols.decimalSeparator = ','
                } else if (groupDecimalMode == GroupDecimalMode.COMMA) {
                    symbols.groupingSeparator = ','
                    symbols.decimalSeparator = '.'
                }
            }
            val pattern = StringBuilder("#,###")
            if (numberAfterDot > 0) {
                var patternAfterDot = "#"
                if (afterDotMode == AfterDotMode.ALWAYS_ZERO) {
                    patternAfterDot = "0"
                }
                pattern.append(".")
                for (i in 0 until numberAfterDot) {
                    pattern.append(patternAfterDot)
                }

            }
            val formatter = DecimalFormat(pattern.toString(), symbols)
            formatter.roundingMode = RoundingMode.DOWN
            return formatter.format(value)
        }

        @JvmStatic
        fun roundNumber(value: Double, numberDigit: Int, roundMode: RoundMode): Double {
            return BigDecimal.valueOf(value).setScale(numberDigit, RoundMode.getRoundMode(roundMode)).toDouble()
        }
    }
}