package com.inmobly.shopping.core.data.entity.products


import com.google.gson.annotations.SerializedName

data class HomeResponseEntity(
    @SerializedName("productLists")
    var homeSections: List<HomeSectionEntity?>
)