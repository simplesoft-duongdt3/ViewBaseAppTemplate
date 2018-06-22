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

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import viewbase.app.demo.com.viewbaseapp.base.exception.EmptyOutputException

/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This interface represents a execution unit for different use cases (this means any use case
 * in the application should implement this contract).
 *
 * By convention each UseCase implementation will return the result using a [DisposableObserver]
 * that will executeAsync its job in a background thread and will post the result in the UI thread.
 */
abstract class UseCase<in Input, Output> internal constructor(private val useCaseExecution: UseCaseExecution) {
    private var disposables: CompositeDisposable = CompositeDisposable()

    /**
     * Builds an [Observable] which will be used when executing the current [UseCase].
     */
    internal abstract fun buildUseCaseObservable(input: Input): Single<Output>

    /**
     * Executes the current use case.
     *
     * @param observer [DisposableObserver] which will be listening to the observable build
     * by [.buildUseCaseObservable] ()} method.
     * @param input Parameters (Optional) used to build/executeAsync this use case.
     */
    fun executeAsync(resultListener: ResultListener<Output>, input: Input): UseCaseTask {
        val observer: DefaultObserver<Output> = DefaultObserver()
        observer.addResultListener(ViewResponseResultListener(resultListener))

        val observable = this.buildUseCaseObservable(input)
                .subscribeOn(useCaseExecution.execution)
                .observeOn(useCaseExecution.postExecution)

        val disposable = observable.subscribeWith(observer)
        addDisposable(disposable)
        return UseCaseTask(disposable)
    }

    @Throws(Throwable::class)
    fun execute(input: Input): Output {
        val outputObservable = OutputObservable<Output>()

        this.buildUseCaseObservable(input).subscribeWith(outputObservable)
        if (outputObservable.output != null) {
            return outputObservable.output!!
        }
        throw if (outputObservable.exception != null) {
            outputObservable.exception!!
        } else EmptyOutputException()
    }

    /**
     * Dispose from current [CompositeDisposable].
     */
    fun cancel() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
        disposables = CompositeDisposable()
    }

    /**
     * Dispose from current [CompositeDisposable].
     */
    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }
}
