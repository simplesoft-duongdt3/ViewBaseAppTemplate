package viewbase.app.demo.com.viewbaseapp.presentation.features.demo.pager.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import viewbase.app.demo.com.viewbaseapp.R
import viewbase.app.demo.com.viewbaseapp.base.viewbase.ViewController


class Pager1ViewController(bundle: Bundle?) : ViewController(bundle) {
    constructor() : this(null)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        return inflater.inflate(R.layout.header_home, container, false)
    }
}