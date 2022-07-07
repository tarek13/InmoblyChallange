package com.inmobly.common_ui.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class StateListener @Inject constructor() {
    private val _unAuthorizedErrorSingleLiveEvent = SingleLiveEvent<Boolean?>()
    val unAuthorizedErrorLiveData: LiveData<Boolean?> = _unAuthorizedErrorSingleLiveEvent

    private val _errorMessageSingleLiveEvent = SingleLiveEvent<Any?>()
    val errorMessageLiveData: LiveData<Any?> = _errorMessageSingleLiveEvent

    private val _loadingProgressSingleLiveEvent = SingleLiveEvent<Boolean?>()
    val loadingProgressLiveData: LiveData<Boolean?> = _loadingProgressSingleLiveEvent

    private val _successResponseSingleLiveEvent = MutableLiveData<Any?>()
    val successResponseLiveData: LiveData<Any?> = _successResponseSingleLiveEvent

    fun setUnAuthorizedErrorLiveData(unAuthorizedErrorDialog: Boolean?) {
        _unAuthorizedErrorSingleLiveEvent.value = unAuthorizedErrorDialog
    }

    fun setErrorMessageLiveData(errorMessageTextToast: String?) {
        _errorMessageSingleLiveEvent.value = errorMessageTextToast
    }

    fun setErrorMessageLiveData(errorMessageTextToast: Int?) {
        _errorMessageSingleLiveEvent.value = errorMessageTextToast
    }

    fun setLoadingProgressLiveData(loadingStatusProgress: Boolean) {
        _loadingProgressSingleLiveEvent.value = loadingStatusProgress
    }

    fun setSuccessResponse(successResponse: Any?) {
        _successResponseSingleLiveEvent.value = successResponse
    }

}