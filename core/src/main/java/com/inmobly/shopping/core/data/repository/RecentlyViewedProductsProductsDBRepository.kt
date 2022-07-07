package com.inmobly.shopping.core.data.repository

import com.inmobly.shopping.core.data.entity.products.ProductEntity
import com.inmobly.shopping.core.data.source.local.room.dao.RecentlyViewedProductsDao
import com.inmobly.shopping.core.data.util.mapper.*
import com.inmobly.shopping.core.domain.repository.IRecentlyViewedProductsDBRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class RecentlyViewedProductsProductsDBRepository @Inject constructor(
    private val recentlyViewedProductsDao: RecentlyViewedProductsDao,
    private val favoriteProductsProductsDBRepository: FavoriteProductsProductsDBRepository
) : IRecentlyViewedProductsDBRepository {


    override fun saveRecentlyViewedProducts(products: ProductEntity?): Completable {
        return Completable.defer {
            recentlyViewedProductsDao.addProducts(products.mapToRecentlyViewedProduct())
            Completable.complete()
        }
    }

    override fun getRecentlyViewedProducts(): Single<List<ProductEntity?>> {
        return Single.defer {
            Single.just(recentlyViewedProductsDao.getAllProducts()).map { it.mapRecentlyViewedToProductEntityList() }
        }.flatMap {
            recentlyViewedProductsDao.deleteOldProduct()
            Single.just(it)
        }.map {
            it.forEach { p1 ->
                p1?.isFavorite =
                    favoriteProductsProductsDBRepository.getFavoriteProductById(p1?.id) != null
            }
            it
        }
    }

    override fun isNotEmpty(): Boolean {
       return recentlyViewedProductsDao.getAllProducts().isNotEmpty()

    }

    override fun clearRecentlyViewedProducts(): Completable {
        return Completable.defer {
            recentlyViewedProductsDao.clearAllProducts()
            Completable.complete()
        }
    }

}
