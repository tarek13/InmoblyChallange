package com.inmobly.common_ui.utils.helper

import com.inmobly.common_ui.R
import com.inmobly.shopping.core.domain.util.AppConstants



object ErrorMessageHelper {


    fun showGeneralErrorMessage(errorCode: Int): Int {
        when (errorCode) {
            AppConstants.ERROR_CODE_NOT_CONNECTED_TO_INTERNET -> return R.string.not_connected_to_internet
            AppConstants.ERROR_CODE_TIME_OUT -> return R.string.error_msg_timeout
            AppConstants.ERROR_CODE_LOAD_DATA -> return R.string.error_load_data
        }
        return errorCode
    }
}