package com.inmobly.shopping.core.domain.usecase.products

import com.inmobly.shopping.core.data.entity.products.ProductEntity
import com.inmobly.shopping.core.domain.repository.IRecentlyViewedProductsDBRepository
import com.inmobly.shopping.core.domain.usecase.base.CompletableUseCaseWithParams
import com.inmobly.shopping.core.domain.util.SchedulerProvider
import io.reactivex.Completable
import javax.inject.Inject

class AddProductToRecentlyViewedUseCase @Inject constructor(
    private val recentlyViewedProductsProductsDBRepository: IRecentlyViewedProductsDBRepository,
    private val schedulerProvider: SchedulerProvider
) : CompletableUseCaseWithParams<AddProductToRecentlyViewedUseCase.Params>(schedulerProvider) {

    override fun buildUseCaseSingle(params: Params): Completable {
        return recentlyViewedProductsProductsDBRepository.saveRecentlyViewedProducts(params.productEntity)
    }

    data class Params(
        val productEntity: ProductEntity?
    )


}