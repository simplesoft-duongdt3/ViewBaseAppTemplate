package viewbase.app.demo.com.viewbaseapp.base.listview.view

import android.view.View

/**
 * Created by leducanh on 20/12/2017.
 */
interface OnItemRvClickedListener<in D> {
    fun onItemClicked(view: View, position: Int, dataItem: D)
}