package viewbase.app.demo.com.viewbaseapp.base.exception

class AppException(val errorMessage: String, val errorCode: Int = 1, cause: Throwable? = null) : Exception(errorMessage, cause)