package com.inmobly.shopping.core.data.entity.products


import com.google.gson.annotations.SerializedName

data class ProductResponseEntity(
    @SerializedName("products")
    var products: List<ProductEntity?>
)