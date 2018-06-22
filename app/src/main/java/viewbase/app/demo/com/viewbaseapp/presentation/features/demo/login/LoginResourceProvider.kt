package viewbase.app.demo.com.viewbaseapp.presentation.features.demo.login

import viewbase.app.demo.com.viewbaseapp.R
import viewbase.app.demo.com.viewbaseapp.base.resource.ResourceManager

class LoginResourceProvider(private val resourceManager: ResourceManager) {
    fun getLoginFailErrorMsg() = resourceManager.getString(R.string.login_feature_error_msg_missing_login_info)
    fun getEmailPasswordWrongErrorMsg() = resourceManager.getString(R.string.login_feature_error_msg_wrong_login_info)
}