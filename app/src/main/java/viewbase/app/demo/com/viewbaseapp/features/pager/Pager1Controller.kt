package viewbase.app.demo.com.viewbaseapp.features.pager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import viewbase.app.demo.com.viewbaseapp.R


class Pager1Controller : Controller() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        return inflater.inflate(R.layout.header_home, container, false)
    }
}