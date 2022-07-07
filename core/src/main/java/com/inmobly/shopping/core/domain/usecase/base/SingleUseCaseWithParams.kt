package com.inmobly.shopping.core.domain.usecase.base

import com.inmobly.shopping.core.domain.util.SchedulerProvider
import io.reactivex.Single

abstract class SingleUseCaseWithParams<T, Params>(
    private val schedulerProvider: SchedulerProvider
) : UseCase() {
    var token: String? = null
     abstract fun buildUseCaseSingle(params: Params?): Single<T>
    fun execute(
        params: Params? = null,
        singleUseCaseCallback: SingleUseCaseCallback<T>,
        disposeLast: Boolean = true,
        token: String? = null
    ) {
        this.token = token
        if (disposeLast) {
            disposeLast()
        }
        disposable = buildUseCaseSingle(params)
            .subscribeOn(schedulerProvider.io)
            .observeOn(schedulerProvider.mainThread)
            .subscribe({ t: T -> singleUseCaseCallback.onSuccess(t) }) { throwable: Throwable ->
                singleUseCaseCallback.onError(
                    throwable
                )
            }
        disposable?.let {
            compositeDisposable.add(it)
        }
    }
}