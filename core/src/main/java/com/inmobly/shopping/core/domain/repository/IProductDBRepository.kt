package com.inmobly.shopping.core.domain.repository

import com.inmobly.shopping.core.data.entity.products.HomeSectionEntity
import com.inmobly.shopping.core.data.entity.products.ProductEntity
import io.reactivex.Completable
import io.reactivex.Single

interface IProductDBRepository {
/*    fun saveproducts(listproducts: List<productModel?>): Completable
    fun getproducts(): Single<List<productModel?>>
    fun isCached(): Single<Boolean>
    fun clearproducts(): Completable*/
    fun setLastCacheTime(lastCache: Long)
    fun isExpired(): Boolean
    fun saveHomeSections(listHomeSections: List<HomeSectionEntity?>): Completable
    fun getHomeSections(): Single<List<HomeSectionEntity?>?>
    fun clearHomeSections(): Completable
    fun isHomeSectionCached(): Single<Boolean>
    fun saveProducts(products: List<ProductEntity?>, sectionId: Int?): Completable
    fun getProducts(sectionId: Int?): Single<List<ProductEntity?>?>
    fun clearProducts(): Completable
    fun isProductsCached(): Single<Boolean>
}