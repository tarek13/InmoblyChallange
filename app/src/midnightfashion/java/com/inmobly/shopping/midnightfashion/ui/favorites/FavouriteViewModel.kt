package com.inmobly.shopping.midnightfashion.ui.favorites

import androidx.lifecycle.ViewModel
import com.inmobly.shopping.core.data.entity.products.ProductEntity
import com.inmobly.shopping.core.domain.usecase.base.SingleUseCaseCallback
import com.inmobly.shopping.core.domain.usecase.products.GetFavoriteProductsUseCase
import com.inmobly.common_ui.utils.StateListener
import com.inmobly.common_ui.utils.helper.ErrorHandlingUtils
import com.inmobly.common_ui.utils.helper.IProductsFavouriteHelper
import com.inmobly.common_ui.utils.helper.ProductFavouriteHelper
import com.inmobly.common_ui.utils.mapper.mapToViewList

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    private val getFavoriteProductsUseCase: GetFavoriteProductsUseCase,
    val stateListener: StateListener,
    private val errorHandlingUtils: ErrorHandlingUtils,
    private val productFavouriteHelper: ProductFavouriteHelper
) : ViewModel() , IProductsFavouriteHelper by  productFavouriteHelper{

    fun loadFavoriteProducts() {
        stateListener.setLoadingProgressLiveData(true)
        getFavoriteProductsUseCase.execute(
            singleUseCaseCallback = object : SingleUseCaseCallback<List<ProductEntity?>> {
                override fun onSuccess(response: List<ProductEntity?>) {
                    stateListener.setLoadingProgressLiveData(false)
                    stateListener.setSuccessResponse(response.mapToViewList())
                }

                override fun onError(throwable: Throwable) {
                    stateListener.setLoadingProgressLiveData(false)
                    errorHandlingUtils.getErrorMessage(throwable, stateListener)
                }

            }
        )
    }


    override fun onCleared() {
        super.onCleared()
        getFavoriteProductsUseCase.dispose()

    }


}