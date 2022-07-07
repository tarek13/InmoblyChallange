package com.inmobly.shopping.core.domain.usecase.products

import com.inmobly.shopping.core.data.entity.products.ProductEntity
import com.inmobly.shopping.core.domain.repository.IFavoriteProductsDBRepository
import com.inmobly.shopping.core.domain.usecase.base.CompletableUseCaseWithParams
import com.inmobly.shopping.core.domain.util.SchedulerProvider
import io.reactivex.Completable
import javax.inject.Inject

class RemoveProductFromFavoriteUseCase @Inject constructor(
    private val favoriteProductsDBRepository: IFavoriteProductsDBRepository,
    private val schedulerProvider: SchedulerProvider
) : CompletableUseCaseWithParams<RemoveProductFromFavoriteUseCase.Params>(schedulerProvider) {

    override fun buildUseCaseSingle(params: Params): Completable {
        return favoriteProductsDBRepository.deleteFavoriteProduct(params.productEntity?.id)
    }

    data class Params(
        val productEntity: ProductEntity?
    )


}