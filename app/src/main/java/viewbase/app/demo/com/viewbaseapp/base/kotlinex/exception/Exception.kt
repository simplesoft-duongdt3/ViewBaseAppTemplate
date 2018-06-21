package viewbase.app.demo.com.viewbaseapp.base.kotlinex.exception

import viewbase.app.demo.com.viewbaseapp.base.exception.AppException

fun Exception.reThrow(errorMessage: String, errorCode: Int = 0) {
    throw AppException(errorMessage, errorCode, this)
}