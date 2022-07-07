package com.inmobly.shopping.core.data.entity.products


import androidx.room.Entity
import androidx.room.Index
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "products",
    primaryKeys = ["id","sectionId"],
    indices = [Index(value = ["id", "sectionId"], unique = true)]
)
data class ProductEntity(
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
    var sectionId: Int,
    var isFavorite: Boolean?
)