package viewbase.app.demo.com.viewbaseapp.base.kotlinex.boolean

fun Boolean?.valueOrDefault(default: Boolean): Boolean {
    return this ?: default
}

fun Boolean?.valueOrFalse(): Boolean {
    return this.valueOrDefault(false)
}