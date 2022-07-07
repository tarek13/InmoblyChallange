package com.inmobly.shopping.core.data.source.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.inmobly.shopping.core.data.entity.products.RecentlyViewedProductEntity

@Dao
interface RecentlyViewedProductsDao {

    @Query("SELECT * FROM recently_viewed where id=:id")
    fun getProduct(id: String?): RecentlyViewedProductEntity?

    @Query("SELECT * from recently_viewed ORDER BY id DESC LIMIT 10")
    fun getAllProducts(): List<RecentlyViewedProductEntity?>

    @Query("DELETE FROM recently_viewed where id NOT IN (SELECT id from recently_viewed ORDER BY id DESC LIMIT 10)")
    fun deleteOldProduct()

    
    @Query("DELETE FROM recently_viewed where productId=:id")
    fun deleteProduct(id: String?)
    
    @Query("DELETE FROM recently_viewed")
    fun clearAllProducts()


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addProducts(productEntity: RecentlyViewedProductEntity?)
}
