package com.inmobly.shopping.core.domain.repository

import com.inmobly.shopping.core.data.entity.ErrorResponseEntity
import com.inmobly.shopping.core.data.entity.products.HomeSectionEntity
import com.inmobly.shopping.core.data.entity.products.ProductEntity
import io.reactivex.Single

interface IProductsRepository {

   /* fun getNews(): Single<List<productModel?>?>
    fun handleLoginErrorResponse(errorBody: String?): ErrorResponseModel?*/
    fun handleLoginErrorResponse(errorBody: String?): ErrorResponseEntity?
    fun getHomeSections(appName: String?): Single<List<HomeSectionEntity?>?>
    fun getProducts(appName: String?, sectionId: Int?): Single<List<ProductEntity?>?>
}