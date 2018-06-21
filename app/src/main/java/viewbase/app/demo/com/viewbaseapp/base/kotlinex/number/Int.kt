package viewbase.app.demo.com.viewbaseapp.base.kotlinex.number

fun Int?.valueOrDefault(default: Int): Int {
    return this ?: default
}

fun Int?.valueOrZero(): Int {
    return this.valueOrDefault(0)
}
