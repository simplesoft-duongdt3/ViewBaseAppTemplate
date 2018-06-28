package viewbase.app.demo.com.viewbaseapp.presentation.features.demo.detail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.header_home.view.*
import viewbase.app.demo.com.viewbaseapp.R
import viewbase.app.demo.com.viewbaseapp.base.eventbus.KBus
import viewbase.app.demo.com.viewbaseapp.base.viewbase.viewcontroller.ViewController


class HeaderViewController(bundle: Bundle?) : ViewController(bundle) {
    constructor() : this(null)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        return inflater.inflate(R.layout.header_home, container, false)
    }

    override fun initPostCreateView(view: View) {
        KBus.subscribe<SelectUserBusEvent>(this, { selectedUser ->
            view.tvHeader.text = "User ${selectedUser.userName} is selected."
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        KBus.unsubscribe(this)
    }

    class SelectUserBusEvent(val userName: String?)
}