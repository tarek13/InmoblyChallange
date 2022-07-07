package com.inmobly.common_ui.utils.mapper


import com.inmobly.shopping.core.data.entity.products.ProductEntity
import com.inmobly.common_ui.model.products.Product

fun ProductEntity?.mapToView(): Product? {
    return this?.run {
        Product(
            description,
            id,
            img,
            name,
            price,
            sectionId,
            isFavorite
        )
    }
}

fun List<ProductEntity?>?.mapToViewList(): List<Product?> {
    val viewList = mutableListOf<Product?>()
    this?.forEach { model ->
        viewList.add(model.mapToView())
    }
    return viewList
}

fun Product?.mapToEntity(): ProductEntity? {
    return this?.run {
        ProductEntity(
            description,
            id,
            img,
            name,
            price,
            sectionId!!,
            isFavorite
        )
    }
}

fun List<Product?>?.mapToEntityList(): List<ProductEntity?> {
    val viewList = mutableListOf<ProductEntity?>()
    this?.forEach { model ->
        viewList.add(model.mapToEntity())
    }
    return viewList
}