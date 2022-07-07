package com.inmobly.shopping.core.data.util

import com.google.gson.Gson
import com.inmobly.shopping.core.data.entity.ErrorResponseEntity
import javax.inject.Inject

class HandleApiErrors @Inject constructor(private val gson: Gson) {

    fun handleErrorResponse(errorBody: String?): ErrorResponseEntity? {
        val error: ErrorResponseEntity?
        try {

            error = gson.fromJson(errorBody, ErrorResponseEntity::class.java)
        } catch (e: Exception) {
            return null
        }
        return error
    }


}