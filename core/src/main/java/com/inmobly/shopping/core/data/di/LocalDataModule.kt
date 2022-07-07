package com.inmobly.shopping.core.data.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.inmobly.shopping.core.data.source.local.room.AppDatabase
import com.inmobly.shopping.core.data.source.local.room.dao.CachedHomeSectionDao
import com.inmobly.shopping.core.data.source.local.room.dao.CachedProductsDao
import com.inmobly.shopping.core.data.source.local.room.dao.FavoriteProductsDao
import com.inmobly.shopping.core.data.source.local.room.dao.RecentlyViewedProductsDao
import com.inmobly.shopping.core.data.source.local.shared_prefs.AppPreferencesHelper
import com.inmobly.shopping.core.data.source.local.shared_prefs.PreferencesHelper
import com.inmobly.shopping.core.domain.util.AppConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class LocalDataModule {


    @Provides
    @Singleton
    fun providerAppPrefs(
        @ApplicationContext context: Context,
        gson: Gson,
        @Named("SHARED_PREFS") name: String
    ): PreferencesHelper {
        return AppPreferencesHelper(context, name, gson)
    }



    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context,
        @Named("DB_NAME") name: String
    ): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java, name
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideCachedProductsDao(appDatabase: AppDatabase): CachedProductsDao {
        return appDatabase.cachedProductsDao()
    }

    @Singleton
    @Provides
    fun provideCachedHomeSectionDao(appDatabase: AppDatabase): CachedHomeSectionDao {
        return appDatabase.cachedHomeSectionDao()
    }


    @Singleton
    @Provides
    fun provideFavoriteProductsDao(appDatabase: AppDatabase): FavoriteProductsDao {
        return appDatabase.favoriteProductsDao()
    }

    @Singleton
    @Provides
    fun provideRecentlyViewedProductsDao(appDatabase: AppDatabase): RecentlyViewedProductsDao {
        return appDatabase.recentlyViewedProductsDao()
    }
}