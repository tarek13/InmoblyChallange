package com.inmobly.shopping.core.domain.usecase.base

interface SingleUseCaseCallback<T> {
    fun onSuccess(response: T)
    fun onError(throwable: Throwable)
}