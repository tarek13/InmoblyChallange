package com.inmobly.shopping.core.data.entity.products


import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "home_sections", primaryKeys = ["id"])
data class HomeSectionEntity(
    @SerializedName("id")
    var id: Int?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("style")
    var style: String?
)