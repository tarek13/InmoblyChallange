package com.inmobly.shopping.core.data.repository

import com.inmobly.shopping.core.data.entity.products.ProductEntity
import com.inmobly.shopping.core.data.source.local.room.dao.FavoriteProductsDao
import com.inmobly.shopping.core.data.util.mapper.mapToFavoriteProduct
import com.inmobly.shopping.core.data.util.mapper.mapToProductEntity
import com.inmobly.shopping.core.data.util.mapper.mapToProductEntityList
import com.inmobly.shopping.core.domain.repository.IFavoriteProductsDBRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class FavoriteProductsProductsDBRepository @Inject constructor(
    private val favoriteProductsDao: FavoriteProductsDao,
) : IFavoriteProductsDBRepository {


    override fun saveFavoriteProducts(products: ProductEntity?): Completable {
        return Completable.defer {
            favoriteProductsDao.addProducts(products.mapToFavoriteProduct())
            Completable.complete()
        }
    }

    override fun getFavoriteProducts(): Single<List<ProductEntity?>> {
        return Single.defer {
            Single.just(favoriteProductsDao.getAllProducts()).map { it.mapToProductEntityList() }
        }
    }

    override fun getFavoriteProductById(productId: String?):ProductEntity? {
        return favoriteProductsDao.getProduct(productId).mapToProductEntity()
    }

    override fun clearFavoriteProducts(): Completable {
        return Completable.defer {
            favoriteProductsDao.clearAllProducts()
            Completable.complete()
        }
    }

    override fun deleteFavoriteProduct(productId: String?): Completable {
        return Completable.defer {
            favoriteProductsDao.deleteProduct(productId)
            Completable.complete()
        }
    }

}
