package com.inmobly.shopping.core.data.repository

import com.inmobly.shopping.core.data.entity.ErrorResponseEntity
import com.inmobly.shopping.core.data.entity.products.HomeResponseEntity
import com.inmobly.shopping.core.data.entity.products.HomeSectionEntity
import com.inmobly.shopping.core.data.entity.products.ProductEntity
import com.inmobly.shopping.core.data.source.remote.ProductsApis
import com.inmobly.shopping.core.data.util.HandleApiErrors
import com.inmobly.shopping.core.data.util.NetworkUtils
import com.inmobly.shopping.core.domain.repository.IProductsRepository
import com.inmobly.shopping.core.domain.util.AppConstants
import io.reactivex.Single
import java.util.concurrent.TimeoutException
import javax.inject.Inject
import javax.inject.Named

class ProductsRepository @Inject constructor(
    private val productsApis: ProductsApis,
    private val dbRepository: ProductDBRepository,
    @param:Named("current_language")
    private val currentLanguage: String,
    private val handleApiErrors: HandleApiErrors,
    private val favoriteProductsDBRepository: FavoriteProductsProductsDBRepository,
    private val recentlyViewedProductsProductsDBRepository: RecentlyViewedProductsProductsDBRepository,
    private val networkUtils: NetworkUtils
) : IProductsRepository {

    override fun getHomeSections(appName: String?): Single<List<HomeSectionEntity?>?> {

        return productsApis.getHomeSections(appName).map {
            addRecentlyViewedSection(it)
        }.flatMap {
            saveHomeSectionToDb(it)
        }.onErrorResumeNext {
            getHomeSectionFromDB(it)
        }
    }

    private fun saveHomeSectionToDb(list: List<HomeSectionEntity?>): Single<List<HomeSectionEntity?>> {
        return dbRepository.saveHomeSections(list).doOnComplete {
            dbRepository.setLastCacheTime(System.currentTimeMillis())
        }.toSingle { list }
    }

    private fun addRecentlyViewedSection(homeResponseEntity: HomeResponseEntity): List<HomeSectionEntity?> {
        val result = homeResponseEntity.homeSections.toMutableList()
        if (recentlyViewedProductsProductsDBRepository.isNotEmpty()) {
            result.add(
                1,
                HomeSectionEntity(-1, "Recently viewed", AppConstants.REGULAR_CARD_STYLE)
            )
        }
        return result.toList()
    }

    private fun getHomeSectionFromDB(throwable: Throwable): Single<List<HomeSectionEntity?>?> {
        return if (!networkUtils.checkForInternetConnected() || throwable is TimeoutException) {
            dbRepository.getHomeSections()
        } else {
            Single.error(throwable)
        }
    }


    override fun getProducts(appName: String?, sectionId: Int?): Single<List<ProductEntity?>?> {

        return productsApis.getSectionProducts(appName, sectionId).map {
            it.products.toMutableList()
        }.flatMap {
            dbRepository.saveProducts(it, sectionId).toSingle { it.toList() }
        }.onErrorResumeNext {
            getProductSectionFromDB(it, sectionId)
        }.map {
            checkProductIsFavorite(it)
        }


    }

    private fun checkProductIsFavorite(productList: List<ProductEntity?>): List<ProductEntity?> {
        productList.forEach { product ->
            product?.isFavorite =
                favoriteProductsDBRepository.getFavoriteProductById(product?.id) != null
        }
        return productList
    }

    private fun getProductSectionFromDB(
        throwable: Throwable,
        sectionId: Int?
    ): Single<List<ProductEntity?>?> {
        return if (!networkUtils.checkForInternetConnected() || throwable is TimeoutException) {
            dbRepository.getProducts(sectionId)
        } else {
            Single.error(throwable)
        }
    }


    override fun handleLoginErrorResponse(errorBody: String?): ErrorResponseEntity? {
        return handleApiErrors.handleErrorResponse(errorBody)
    }
}