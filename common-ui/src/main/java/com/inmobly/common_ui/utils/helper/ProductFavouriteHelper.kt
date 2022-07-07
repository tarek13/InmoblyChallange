package com.inmobly.common_ui.utils.helper

import androidx.lifecycle.LiveData
import com.inmobly.shopping.core.domain.usecase.base.CompletableUseCaseCallback
import com.inmobly.shopping.core.domain.usecase.products.RemoveProductFromFavoriteUseCase
import com.inmobly.shopping.core.domain.usecase.products.SaveProductToFavoriteUseCase
import com.inmobly.common_ui.model.products.Product
import com.inmobly.common_ui.utils.SingleLiveEvent
import com.inmobly.common_ui.utils.mapper.mapToEntity
import javax.inject.Inject

class ProductFavouriteHelper @Inject constructor(
    private val saveProductToFavoriteUseCase: SaveProductToFavoriteUseCase,
    private val removeProductFromFavoriteUseCase: RemoveProductFromFavoriteUseCase
) : IProductsFavouriteHelper {
    private val _isFavoriteSingleLiveEvent = SingleLiveEvent<Pair<Int,Boolean>?>()
    private val productIsFavoriteLiveData: LiveData<Pair<Int, Boolean>?> = _isFavoriteSingleLiveEvent

    override fun productIsFavoriteLiveData():LiveData<Pair<Int, Boolean>?>{
        return productIsFavoriteLiveData
    }
    override fun addProductToFavorites(product: Product?, position: Int) {
        saveProductToFavoriteUseCase.execute(
            params = SaveProductToFavoriteUseCase.Params(product.mapToEntity()),
            completableUseCaseCallback = object : CompletableUseCaseCallback {
                override fun onSuccess() {
                    _isFavoriteSingleLiveEvent.value = Pair(position, true)
                }

                override fun onError(throwable: Throwable) {
                    _isFavoriteSingleLiveEvent.value = Pair(position, false)
                }

            }
        )
    }

    override fun removeFromFavorite(product: Product?, position: Int) {
        removeProductFromFavoriteUseCase.execute(
            params = RemoveProductFromFavoriteUseCase.Params(product.mapToEntity()),
            completableUseCaseCallback = object : CompletableUseCaseCallback {
                override fun onSuccess() {
                    _isFavoriteSingleLiveEvent.value = Pair(position, true)
                }

                override fun onError(throwable: Throwable) {
                    _isFavoriteSingleLiveEvent.value = Pair(position, false)
                }

            }
        )
    }
}