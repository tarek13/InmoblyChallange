package com.inmobly.shopping.core.domain.usecase.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class UseCase {
    protected var disposable: Disposable? = null
    protected var compositeDisposable = CompositeDisposable()
    fun disposeLast() {
        if (disposable != null && !disposable!!.isDisposed) {
            disposable?.dispose()
        }
    }

    fun dispose() {
        compositeDisposable.clear()
    }
}