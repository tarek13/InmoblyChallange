package com.inmobly.shopping.core.domain.usecase.base

interface CompletableUseCaseCallback {
    fun onSuccess()
    fun onError(throwable: Throwable)
}