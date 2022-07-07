package com.inmobly.shopping.core.data.entity.products

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "favorite_products", primaryKeys = ["id"])
data class FavouriteProductEntity(
    @SerializedName("description")
    var description: String?,
    @SerializedName("id")
    var id: String,
    @SerializedName("img")
    var img: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("price")
    var price: Double?,
    var sectionId: Int?
)