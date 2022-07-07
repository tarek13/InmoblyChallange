package com.inmobly.shopping.core.data.source.remote

import com.inmobly.shopping.core.data.entity.products.HomeResponseEntity
import com.inmobly.shopping.core.data.entity.products.ProductResponseEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductsApis {
    @GET("{app_name}/home.json")
    fun getHomeSections(
        @Path("app_name") appName: String?
    ): Single<HomeResponseEntity?>

    @GET("{app_name}/list/list{section_id}.json")
    fun getSectionProducts(
        @Path("app_name") appName: String?,
        @Path("section_id") id: Int?
    ):Single<ProductResponseEntity>
}