package viewbase.app.demo.com.viewbaseapp.base.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.loadinger_loading_view.view.*
import viewbase.app.demo.com.viewbaseapp.R

/**
 * Custom Alert View
 *
 * @author Kevin Murphy, Tapadoo, Dublin, Ireland, Europe, Earth.
 * @since 26/01/2016
 */
class LoadingLayout : FrameLayout {

    /**
     * This is the default view constructor. It requires a Context, and holds a reference to it.
     * If not cleaned up properly, memory will leak.
     *
     * @param context The Activity Context
     */
    constructor(context: Context) : super(context, null, R.attr.alertStyle) {
        initView()
    }

    /**
     * This is the default view constructor. It requires a Context, and holds a reference to it.
     * If not cleaned up properly, memory will leak.
     *
     * @param context The Activity Context
     * @param attrs   View Attributes
     */
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs, R.attr.alertStyle) {
        initView()
    }

    /**
     * This is the default view constructor. It requires a Context, and holds a reference to it.
     * If not cleaned up properly, memory will leak.
     *
     * @param context      The Activity Context
     * @param attrs        View Attributes
     * @param defStyleAttr Styles
     */
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView()
    }

    private fun initView() {
        visibility = View.GONE
        View.inflate(context, R.layout.loadinger_loading_view, this)
        isHapticFeedbackEnabled = true

        this.pbLoading.onViewDisplayEvent = { this@LoadingLayout.visibility = View.VISIBLE }
        this.pbLoading.onViewHideEvent = { this@LoadingLayout.visibility = View.GONE }

    }

    /* Clean Up Methods */

    /**
     * Cleans up the currently showing alert view.
     */
    fun hide() {
        this.pbLoading.hide()
    }

    fun show() {
        this.pbLoading.show()
    }
}