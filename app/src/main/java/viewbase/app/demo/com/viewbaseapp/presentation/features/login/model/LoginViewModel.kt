package viewbase.app.demo.com.viewbaseapp.presentation.features.login.model

class LoginViewModel(val email: String, val pass: String) {
    fun isEnoughInfo(): Boolean {
        return email.isNotEmpty() && pass.isNotEmpty()
    }
}