package viewbase.app.demo.com.viewbaseapp.presentation.features.pager.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.support.RouterPagerAdapter
import kotlinx.android.synthetic.main.screen_feature_pager.view.*
import viewbase.app.demo.com.viewbaseapp.R
import viewbase.app.demo.com.viewbaseapp.base.viewbase.ViewController


class PagerScreenViewController(bundle: Bundle?) : ViewController(bundle) {

    constructor() : this(null)

    private lateinit var routerPagerAdapter: RouterPagerAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.screen_feature_pager, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View) {
        routerPagerAdapter = object : RouterPagerAdapter(this) {
            override fun configureRouter(router: Router, position: Int) {
                if (!router.hasRootController()) {
                    when (position) {
                        0 -> {
                            router.setRoot(RouterTransaction.with(Pager1ViewController()))
                        }
                        1 -> {
                            router.setRoot(RouterTransaction.with(Pager2ViewController()))
                        }
                        2 -> {
                            router.setRoot(RouterTransaction.with(Pager3ViewController()))
                        }
                    }
                }
            }

            override fun getCount(): Int {
                return 3
            }

            override fun getPageTitle(position: Int): CharSequence? {
                return when (position) {
                    0 -> {
                        "Page 1"
                    }
                    1 -> {
                        "Page 2"
                    }
                    2 -> {
                        "Page 2"
                    }
                    else -> {
                        null
                    }
                }
            }
        }

        view.viewPager.adapter = routerPagerAdapter
        view.tabLayout.setupWithViewPager(view.viewPager)
    }

    override fun onDestroyView(view: View) {
        super.onDestroyView(view)
        view.viewPager?.adapter = null
    }
}