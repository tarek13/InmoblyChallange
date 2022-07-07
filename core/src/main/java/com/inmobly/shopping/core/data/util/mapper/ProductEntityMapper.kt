package com.inmobly.shopping.core.data.util.mapper


import com.inmobly.shopping.core.data.entity.products.FavouriteProductEntity
import com.inmobly.shopping.core.data.entity.products.ProductEntity
import com.inmobly.shopping.core.data.entity.products.RecentlyViewedProductEntity


fun ProductEntity?.mapToFavoriteProduct(): FavouriteProductEntity? {
    return this?.run {
        FavouriteProductEntity(
            description,
            id,
            img,
            name,
            price,
            sectionId,
        )
    }
}

fun FavouriteProductEntity?.mapToProductEntity(): ProductEntity? {
    return this?.run {
        ProductEntity(
            description,
            id,
            img,
            name,
            price,
            sectionId!!,
            true,
        )
    }
}

fun List<ProductEntity?>?.mapToFavoriteProductList(): List<FavouriteProductEntity?> {
    val viewList = mutableListOf<FavouriteProductEntity?>()
    this?.forEach { model ->
        viewList.add(model.mapToFavoriteProduct())
    }
    return viewList
}

fun List<FavouriteProductEntity?>?.mapToProductEntityList(): List<ProductEntity?> {
    val viewList = mutableListOf<ProductEntity?>()
    this?.forEach { model ->
        viewList.add(model.mapToProductEntity())
    }
    return viewList
}

fun ProductEntity?.mapToRecentlyViewedProduct(): RecentlyViewedProductEntity? {
    return this?.run {
        RecentlyViewedProductEntity(
            null,
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

fun RecentlyViewedProductEntity?.mapToProductEntity(): ProductEntity? {
    return this?.run {
        ProductEntity(
            description,
            productId,
            img,
            name,
            price,
            sectionId!!,
            isFavorite,
        )
    }
}

fun List<RecentlyViewedProductEntity?>?.mapRecentlyViewedToProductEntityList(): List<ProductEntity?> {
    val viewList = mutableListOf<ProductEntity?>()
    this?.forEach { model ->
        viewList.add(model.mapToProductEntity())
    }
    return viewList
}
