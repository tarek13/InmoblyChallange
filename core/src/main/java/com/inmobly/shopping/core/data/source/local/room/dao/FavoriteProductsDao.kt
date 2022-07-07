package com.inmobly.shopping.core.data.source.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.inmobly.shopping.core.data.entity.products.FavouriteProductEntity

@Dao
interface FavoriteProductsDao {

    @Query("SELECT * FROM favorite_products where id=:id")
    fun getProduct(id: String?): FavouriteProductEntity?

    @Query("SELECT * FROM favorite_products")
    fun getAllProducts(): List<FavouriteProductEntity?>
    
    @Query("DELETE FROM favorite_products where id=:id")
    fun deleteProduct(id: String?)
    
    @Query("DELETE FROM favorite_products")
    fun clearAllProducts()


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addProducts(productEntity: FavouriteProductEntity?)
}
