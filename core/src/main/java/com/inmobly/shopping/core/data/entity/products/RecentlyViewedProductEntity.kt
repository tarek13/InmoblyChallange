package com.inmobly.shopping.core.data.entity.products

import androidx.room.Entity
import androidx.room.Index

@Entity(tableName = "recently_viewed", primaryKeys = ["id"], indices = [Index(value = ["productId"], unique = true)])
data class RecentlyViewedProductEntity(
    var id:Int?,
    var description: String?,
    var productId: String,
    var img: String?,
    var name: String?,
    var price: Double?,
    var sectionId: Int?,
    var isFavorite: Boolean?
)