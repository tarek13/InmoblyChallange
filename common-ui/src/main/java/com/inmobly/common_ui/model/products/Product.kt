package com.inmobly.common_ui.model.products


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    var description: String?,
    var id: String,
    var img: String?,
    var name: String?,
    var price: Double?,
    var sectionId: Int?,
    var isFavorite:Boolean?
):Parcelable