package viewbase.app.demo.com.viewbaseapp.base.extra

import android.os.Bundle
import java.io.Serializable
import kotlin.reflect.KProperty

abstract class BundleOptionsCompanion<out BundleOptions>(
        @PublishedApi internal val intentOptions: BundleOptions
) {

    inline fun bundle(configure: BundleOptions.(Bundle) -> Unit): Bundle =
            Bundle().apply {
                configure(intentOptions, this)
            }

    inline fun <T> Bundle.options(block: BundleOptions.(Bundle) -> T): T =
            block(intentOptions, this)
}

class BundleExtraString(private val name: String) {

    operator fun getValue(bundle: Bundle, property: KProperty<*>): String? =
            bundle.getString(name)

    operator fun setValue(bundle: Bundle, property: KProperty<*>, value: String?) {
        bundle.putString(name, value)
    }
}

class BundleExtraInt(private val name: String) {

    operator fun getValue(bundle: Bundle, property: KProperty<*>): Int =
            bundle.getInt(name)

    operator fun setValue(bundle: Bundle, property: KProperty<*>, value: Int) {
        bundle.putInt(name, value)
    }
}

class BundleExtraLong(private val name: String) {

    operator fun getValue(bundle: Bundle, property: KProperty<*>): Long =
            bundle.getLong(name)

    operator fun setValue(bundle: Bundle, property: KProperty<*>, value: Long) {
        bundle.putLong(name, value)
    }
}

class BundleExtraDouble(private val name: String) {

    operator fun getValue(bundle: Bundle, property: KProperty<*>): Double =
            bundle.getDouble(name)

    operator fun setValue(bundle: Bundle, property: KProperty<*>, value: Double) {
        bundle.putDouble(name, value)
    }
}

class BundleExtraObject<T : Serializable>(private val name: String) {

    operator fun getValue(bundle: Bundle, property: KProperty<*>): T? =
            bundle.getSerializable(name) as T?

    operator fun setValue(bundle: Bundle, property: KProperty<*>, value: T?) {
        bundle.putSerializable(name, value)
    }
}