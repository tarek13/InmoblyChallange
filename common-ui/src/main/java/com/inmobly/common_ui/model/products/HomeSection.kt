package com.inmobly.common_ui.model.products


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HomeSection(
    var id: Int?,
    var name: String?,
    var style: String?
):Parcelable