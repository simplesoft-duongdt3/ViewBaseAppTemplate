package viewbase.app.demo.com.viewbaseapp.base.kotlinex.number

fun Long?.valueOrDefault(default: Long): Long {
    return this ?: default
}

fun Long?.valueOrZero(): Long {
    return this.valueOrDefault(0)
}
