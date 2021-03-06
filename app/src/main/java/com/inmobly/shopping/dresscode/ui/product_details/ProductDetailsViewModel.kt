package com.inmobly.shopping.dresscode.ui.product_details

import androidx.lifecycle.ViewModel
import com.inmobly.shopping.core.domain.usecase.base.CompletableUseCaseCallback
import com.inmobly.shopping.core.domain.usecase.products.AddProductToRecentlyViewedUseCase
import com.inmobly.common_ui.model.products.Product
import com.inmobly.common_ui.utils.helper.IProductsFavouriteHelper
import com.inmobly.common_ui.utils.helper.ProductFavouriteHelper
import com.inmobly.common_ui.utils.mapper.mapToEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val productFavouriteHelper: ProductFavouriteHelper,
    private val addProductToRecentlyViewedUseCase: AddProductToRecentlyViewedUseCase
) : ViewModel(), IProductsFavouriteHelper by productFavouriteHelper {

    override fun addProductToFavorites(product: Product?, position: Int) {

        addProductToRecentlyViewedUseCase.execute(
            params = AddProductToRecentlyViewedUseCase.Params(product.mapToEntity()),
            completableUseCaseCallback = object : CompletableUseCaseCallback {
                override fun onSuccess() {
                    //  _isFavoriteSingleLiveEvent.value = Pair(position, true)
                }

                override fun onError(throwable: Throwable) {
                    //_isFavoriteSingleLiveEvent.value = Pair(position, false)
                }

            }
        )
    }

}