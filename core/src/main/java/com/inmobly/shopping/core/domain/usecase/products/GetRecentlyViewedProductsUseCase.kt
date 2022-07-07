package com.inmobly.shopping.core.domain.usecase.products

import com.inmobly.shopping.core.data.entity.products.ProductEntity
import com.inmobly.shopping.core.domain.repository.IRecentlyViewedProductsDBRepository
import com.inmobly.shopping.core.domain.usecase.base.SingleUseCaseWithParams
import com.inmobly.shopping.core.domain.util.SchedulerProvider
import io.reactivex.Single
import javax.inject.Inject

class GetRecentlyViewedProductsUseCase @Inject constructor(
    private val recentlyViewedProductsProductsDBRepository: IRecentlyViewedProductsDBRepository,
    private val schedulerProvider: SchedulerProvider
) : SingleUseCaseWithParams<List<ProductEntity?>, Any?>(schedulerProvider) {

    override fun buildUseCaseSingle(params: Any?): Single<List<ProductEntity?>> {
        return recentlyViewedProductsProductsDBRepository.getRecentlyViewedProducts()
    }


}