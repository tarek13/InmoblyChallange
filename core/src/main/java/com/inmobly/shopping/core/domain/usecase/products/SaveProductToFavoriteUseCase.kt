package com.inmobly.shopping.core.domain.usecase.products

import com.inmobly.shopping.core.data.entity.products.ProductEntity
import com.inmobly.shopping.core.domain.repository.IFavoriteProductsDBRepository
import com.inmobly.shopping.core.domain.usecase.base.CompletableUseCaseWithParams
import com.inmobly.shopping.core.domain.util.SchedulerProvider
import io.reactivex.Completable
import javax.inject.Inject

class SaveProductToFavoriteUseCase @Inject constructor(
    private val favoriteProductsDBRepository: IFavoriteProductsDBRepository,
    private val schedulerProvider: SchedulerProvider
) : CompletableUseCaseWithParams<SaveProductToFavoriteUseCase.Params>(schedulerProvider) {

    override fun buildUseCaseSingle(params: Params): Completable {
        return favoriteProductsDBRepository.saveFavoriteProducts(params.productEntity)
    }

    data class Params(
        val productEntity: ProductEntity?
    )


}