/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package viewbase.app.demo.com.viewbaseapp.domain.usecase.base

import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver

/**
 * Default [DisposableObserver] base class to be used whenever you want default error handling.
 */
open class DefaultObserver<T> : DisposableSingleObserver<T>() {
    private val resultListeners = mutableListOf<RawResultListener<T>>()

    fun addResultListener(resultListener: RawResultListener<T>) {
        resultListeners.add(resultListener)
    }

    override fun onSuccess(data: T) {
        fireSuccess(data)
    }

    override fun onError(throwable: Throwable) {
        fireFail(throwable)
    }

    private fun fireSuccess(data: T) {
        resultListeners.forEach { resultListener -> resultListener.success(data) }
    }

    private fun fireFail(throwable: Throwable) {
        resultListeners.forEach { resultListener -> resultListener.fail(throwable) }
    }
}
