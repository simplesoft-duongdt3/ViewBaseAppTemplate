package viewbase.app.demo.com.viewbaseapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import kotlinx.android.synthetic.main.activity_main.*
import viewbase.app.demo.com.viewbaseapp.features.login.LoginScreenController

class MainActivity : AppCompatActivity() {

    private lateinit var router : Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        router = Conductor.attachRouter(this, this.controllerContainer, savedInstanceState)
        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(LoginScreenController()))
        }
    }

    override fun onBackPressed() {
        if (!router.handleBack()) {
            super.onBackPressed()
        }
    }
}
