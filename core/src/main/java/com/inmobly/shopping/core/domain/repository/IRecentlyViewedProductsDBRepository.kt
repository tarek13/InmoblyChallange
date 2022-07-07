package com.inmobly.shopping.core.domain.repository

import com.inmobly.shopping.core.data.entity.products.ProductEntity
import io.reactivex.Completable
import io.reactivex.Single

interface IRecentlyViewedProductsDBRepository {
    fun saveRecentlyViewedProducts(products: ProductEntity?): Completable
    fun getRecentlyViewedProducts(): Single<List<ProductEntity?>>
    fun clearRecentlyViewedProducts(): Completable
    fun isNotEmpty(): Boolean
}