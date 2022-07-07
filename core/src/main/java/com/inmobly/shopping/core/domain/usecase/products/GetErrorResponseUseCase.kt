package com.inmobly.shopping.core.domain.usecase.products



import com.inmobly.shopping.core.data.entity.ErrorResponseEntity
import com.inmobly.shopping.core.domain.repository.IProductsRepository
import javax.inject.Inject

class GetErrorResponseUseCase @Inject constructor(private val productsRepository: IProductsRepository) {

    fun getErrorResponseUseCase(errorBody: String?): ErrorResponseEntity? {
        return productsRepository.handleLoginErrorResponse(errorBody)
    }
}