package viewbase.app.demo.com.viewbaseapp.base.kotlinex.number

fun Double?.valueOrDefault(default: Double): Double {
    return this ?: default
}

fun Double?.valueOrZero(): Double {
    return this.valueOrDefault(0.0)
}
