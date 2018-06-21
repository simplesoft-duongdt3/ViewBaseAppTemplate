package viewbase.app.demo.com.viewbaseapp.features.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import viewbase.app.demo.com.viewbaseapp.R


class ListItemController : Controller() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        return inflater.inflate(R.layout.list_item_home, container, false)
    }
}