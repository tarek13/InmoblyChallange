package com.inmobly.shopping.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.inmobly.shopping.core.data.entity.products.FavouriteProductEntity
import com.inmobly.shopping.core.data.entity.products.HomeSectionEntity
import com.inmobly.shopping.core.data.entity.products.ProductEntity
import com.inmobly.shopping.core.data.entity.products.RecentlyViewedProductEntity
import com.inmobly.shopping.core.data.source.local.room.dao.CachedHomeSectionDao
import com.inmobly.shopping.core.data.source.local.room.dao.CachedProductsDao
import com.inmobly.shopping.core.data.source.local.room.dao.FavoriteProductsDao
import com.inmobly.shopping.core.data.source.local.room.dao.RecentlyViewedProductsDao

@Database(
    entities = [ProductEntity::class,HomeSectionEntity::class,FavouriteProductEntity::class,RecentlyViewedProductEntity::class],
    exportSchema = false,
    version = 3
)
@TypeConverters(
    AppDatabase.Converters::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cachedProductsDao(): CachedProductsDao
    abstract fun cachedHomeSectionDao(): CachedHomeSectionDao
    abstract fun favoriteProductsDao(): FavoriteProductsDao
    abstract fun recentlyViewedProductsDao(): RecentlyViewedProductsDao
    class Converters {
        @TypeConverter
        fun restoreList(listOfString: String?): List<String?>? {
            return Gson().fromJson(listOfString, object : TypeToken<List<String?>?>() {}.getType())
        }

        @TypeConverter
        fun saveList(listOfString: List<String?>?): String? {
            return Gson().toJson(listOfString)
        }
    }
}


