package com.inmobly.shopping.core.data.di

import com.inmobly.shopping.core.data.repository.FavoriteProductsProductsDBRepository
import com.inmobly.shopping.core.data.repository.ProductsRepository
import com.inmobly.shopping.core.data.repository.ProductDBRepository
import com.inmobly.shopping.core.data.repository.RecentlyViewedProductsProductsDBRepository
import com.inmobly.shopping.core.domain.repository.IFavoriteProductsDBRepository
import com.inmobly.shopping.core.domain.repository.IProductsRepository
import com.inmobly.shopping.core.domain.repository.IProductDBRepository
import com.inmobly.shopping.core.domain.repository.IRecentlyViewedProductsDBRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoriesModule {

    @Provides
    @Singleton
    fun provideProductsRepository(productsRepository: ProductsRepository): IProductsRepository {
        return productsRepository
    }

    @Provides
    @Singleton
    fun provideRoomDBRepository(productDBRepository: ProductDBRepository): IProductDBRepository {
        return productDBRepository
    }


    @Provides
    @Singleton
    fun provideFavoriteProductsDBRepository(favoriteProductsDBRepository: FavoriteProductsProductsDBRepository): IFavoriteProductsDBRepository {
        return favoriteProductsDBRepository
    }


    @Provides
    @Singleton
    fun provideRecentlyViewedProductsProductsDBRepository(recentlyViewedProductsProductsDBRepository: RecentlyViewedProductsProductsDBRepository): IRecentlyViewedProductsDBRepository {
        return recentlyViewedProductsProductsDBRepository
    }
}