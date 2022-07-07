package com.inmobly.shopping.core.domain.repository

import com.inmobly.shopping.core.data.entity.products.ProductEntity
import io.reactivex.Completable
import io.reactivex.Single

interface IFavoriteProductsDBRepository {
    fun saveFavoriteProducts(products: ProductEntity?): Completable
    fun getFavoriteProducts(): Single<List<ProductEntity?>>
    fun clearFavoriteProducts(): Completable
    fun getFavoriteProductById(productId: String?): /*Single<*/ProductEntity?/*>?*/
    fun deleteFavoriteProduct(productId: String?): Completable
}