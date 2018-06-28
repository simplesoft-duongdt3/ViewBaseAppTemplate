package viewbase.app.demo.com.viewbaseapp.presentation.features.demo.detail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.header_home.view.*
import org.koin.standalone.inject
import viewbase.app.demo.com.viewbaseapp.R
import viewbase.app.demo.com.viewbaseapp.base.eventbus.KBus
import viewbase.app.demo.com.viewbaseapp.base.resource.ResourceManager
import viewbase.app.demo.com.viewbaseapp.base.util.TemplateUtil
import viewbase.app.demo.com.viewbaseapp.base.viewbase.viewcontroller.ViewController


class HeaderViewController(bundle: Bundle?) : ViewController(bundle) {
    constructor() : this(null)
    private val resourceManager: ResourceManager by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        return inflater.inflate(R.layout.header_home, container, false)
    }

    override fun initPostCreateView(view: View) {
        KBus.subscribe<SelectUserBusEvent>(this, { selectedUser ->
            view.tvHeader.text = TemplateUtil.string(resourceManager.getString(R.string.header_detail_title))
                    .put("user_name", selectedUser.userName)
                    .format()
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        KBus.unsubscribe(this)
    }

    class SelectUserBusEvent(val userName: String?)
}