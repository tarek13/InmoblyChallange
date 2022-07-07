package com.inmobly.common_ui.utils.mapper


import com.inmobly.shopping.core.data.entity.products.HomeSectionEntity
import com.inmobly.common_ui.model.products.HomeSection

fun HomeSectionEntity?.mapToView(): HomeSection? {
    return this?.run {
        HomeSection(
            id,
            name,
            style,
        )
    }
}

fun List<HomeSectionEntity?>?.mapToViewList(): List<HomeSection?> {
    val viewList = mutableListOf<HomeSection?>()
    this?.forEach { model ->
        viewList.add(model.mapToView())
    }
    return viewList
}

