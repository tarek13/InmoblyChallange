package com.inmobly.shopping.core.domain.usecase.base

import com.inmobly.shopping.core.domain.util.SchedulerProvider
import io.reactivex.Completable

abstract class CompletableUseCaseWithParams<Params>(
    private val schedulerProvider: SchedulerProvider
) : UseCase() {
    protected abstract fun buildUseCaseSingle(params: Params): Completable
    fun execute(params: Params, completableUseCaseCallback: CompletableUseCaseCallback) {
        disposeLast()
        disposable = buildUseCaseSingle(params)
            .subscribeOn(schedulerProvider.io)
            .observeOn(schedulerProvider.mainThread)
            .subscribe({ completableUseCaseCallback.onSuccess() }) { throwable: Throwable ->
                completableUseCaseCallback.onError(
                    throwable
                )
            }
        disposable?.let {
            compositeDisposable.add(it)
        }
    }
}