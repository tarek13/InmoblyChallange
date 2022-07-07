package com.inmobly.common_ui.utils.helper

import androidx.lifecycle.LiveData
import com.inmobly.common_ui.model.products.Product

interface IProductsFavouriteHelper {

    fun addProductToFavorites(product: Product?, position: Int=0)
    fun removeFromFavorite(product: Product?, position: Int=0)
    fun productIsFavoriteLiveData(): LiveData<Pair<Int, Boolean>?>
}