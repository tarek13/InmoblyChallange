package com.inmobly.shopping.core.data.repository

import com.inmobly.shopping.core.data.entity.products.HomeSectionEntity
import com.inmobly.shopping.core.data.entity.products.ProductEntity
import com.inmobly.shopping.core.data.source.local.room.dao.CachedHomeSectionDao
import com.inmobly.shopping.core.data.source.local.room.dao.CachedProductsDao
import com.inmobly.shopping.core.data.source.local.shared_prefs.PreferencesHelper
import com.inmobly.shopping.core.domain.repository.IProductDBRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class ProductDBRepository @Inject constructor(
    private val cachedProductsDao: CachedProductsDao,
    private val cachedHomeSectionDao: CachedHomeSectionDao,
    private val preferencesHelper: PreferencesHelper
) : IProductDBRepository {

    override fun saveHomeSections(listHomeSections: List<HomeSectionEntity?>): Completable {
        return Completable.defer {
            listHomeSections.map { it }.forEach {
                cachedHomeSectionDao.addHomeSections(it)
            }
            Completable.complete()
        }
    }

    override fun getHomeSections(): Single<List<HomeSectionEntity?>?> {
        return if (isExpired()) {
            Single.defer {
                cachedHomeSectionDao.clearHomeSections()
                cachedProductsDao.clearProducts()
                Single.just(mutableListOf<HomeSectionEntity>().toList())
            }
        } else {
            Single.defer {
                Single.just(cachedHomeSectionDao.getHomeSections())
            }
        }

    }

    override fun clearHomeSections(): Completable {
        return Completable.defer {
            cachedHomeSectionDao.clearHomeSections()
            Completable.complete()
        }
    }


    override fun isHomeSectionCached(): Single<Boolean> {
        return Single.defer {
            Single.just(cachedHomeSectionDao.getHomeSections().isNotEmpty())
        }
    }

    override fun saveProducts(products: List<ProductEntity?>, sectionId: Int?): Completable {
        return Completable.defer {
            products.map { it }.forEach {
                val r = cachedProductsDao.addProducts(it?.copy(sectionId = sectionId!!))
                System.err.println("sww: $r")
            }
            Completable.complete()
        }
    }

    override fun getProducts(sectionId: Int?): Single<List<ProductEntity?>?> {
        return Single.defer {
            Single.just(cachedProductsDao.getProducts(sectionId))
        }
    }

    override fun clearProducts(): Completable {
        return Completable.defer {
            cachedProductsDao.clearProducts()
            Completable.complete()
        }
    }


    override fun isProductsCached(): Single<Boolean> {
        return Single.defer {
            Single.just(cachedProductsDao.getAllProducts().isNotEmpty())
        }
    }

    override fun setLastCacheTime(lastCache: Long) {
        preferencesHelper.lastCacheTime = lastCache
    }

    override fun isExpired(): Boolean {
        val currentTime = System.currentTimeMillis()
        val lastUpdateTime = this.getLastCacheUpdateTimeMillis()
        return currentTime - lastUpdateTime > EXPIRATION_TIME
    }

    /**
     * Get in millis, the last time the cache was accessed.
     */
    private fun getLastCacheUpdateTimeMillis(): Long {
        return preferencesHelper.lastCacheTime
    }

    companion object {
        /**
         * Expiration time set to 10 minutes
         */
        const val EXPIRATION_TIME = (60 * 10 * 1000).toLong()
    }
}
