package com.inmobly.common_ui.utils.mapper

import com.inmobly.shopping.core.data.entity.ErrorResponseEntity
import com.link.worldwidenews.model.ErrorResponse

fun ErrorResponseEntity?.mapToView(): ErrorResponse? {
    return this?.run {
        ErrorResponse(
            code,
            message,
            status,
        )
    }
}


