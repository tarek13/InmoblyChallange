package com.inmobly.shopping.core.data.source.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.inmobly.shopping.core.data.entity.products.ProductEntity

@Dao
interface CachedProductsDao {

    @Query("SELECT * FROM products where sectionId=:id")
    fun getProducts(id: Int?): List<ProductEntity?>

    @Query("SELECT * FROM products")
    fun getAllProducts(): List<ProductEntity?>

    @Query("DELETE FROM products")
    fun clearProducts()


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addProducts(productEntity: ProductEntity?) :Long?
}
