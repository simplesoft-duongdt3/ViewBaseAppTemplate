package viewbase.app.demo.com.viewbaseapp.base.listview.model

import android.view.View
import viewbase.app.demo.com.viewbaseapp.base.listview.view.ViewAction
import viewbase.app.demo.com.viewbaseapp.base.listview.view.ViewSize
import viewbase.app.demo.com.viewbaseapp.base.listview.view.ViewSizer

class WidthHeightViewSizer(val numRow: Int, val padding: Int = 0) : ViewSizer {
    override fun size(viewParentSize: ViewSize): ViewAction {
        return object : ViewAction {
            override fun action(view: View) {
                val size = (viewParentSize.width / numRow) - (padding * 2)
                view.layoutParams.width = size
                view.layoutParams.height = size
            }
        }
    }
}