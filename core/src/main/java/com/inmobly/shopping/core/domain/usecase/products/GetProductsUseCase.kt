package com.inmobly.shopping.core.domain.usecase.products

import com.inmobly.shopping.core.data.entity.products.ProductEntity
import com.inmobly.shopping.core.domain.repository.IProductsRepository
import com.inmobly.shopping.core.domain.usecase.base.SingleUseCaseWithParams
import com.inmobly.shopping.core.domain.util.SchedulerProvider
import io.reactivex.Single
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val productsRepository: IProductsRepository,
    private val schedulerProvider: SchedulerProvider
) : SingleUseCaseWithParams<List<ProductEntity?>?, GetProductsUseCase.Params>(schedulerProvider) {

    override fun buildUseCaseSingle(params: Params?): Single<List<ProductEntity?>?> {
        return productsRepository.getProducts(params?.appName, params?.sectionId)
    }

    data class Params(
        var appName: String?,
        val sectionId: Int?
    )
}