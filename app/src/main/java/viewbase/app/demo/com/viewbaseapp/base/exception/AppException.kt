package foody.com.poswaiter.base.domain.exception

class AppException(val errorMessage: String, val errorCode: Int = 1, cause: Throwable? = null) : Exception(errorMessage, cause)