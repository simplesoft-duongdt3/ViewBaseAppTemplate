package viewbase.app.demo.com.viewbaseapp.base.kotlinex.exception

import foody.com.poswaiter.base.domain.exception.AppException

fun Exception.reThrow(errorMessage: String, errorCode: Int = 0) {
    throw AppException(errorMessage, errorCode, this)
}