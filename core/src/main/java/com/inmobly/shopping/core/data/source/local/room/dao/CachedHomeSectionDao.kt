package com.inmobly.shopping.core.data.source.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.inmobly.shopping.core.data.entity.products.HomeSectionEntity

@Dao
interface CachedHomeSectionDao {

    @Query("SELECT * FROM home_sections")
    fun getHomeSections(): List<HomeSectionEntity?>


    @Query("DELETE FROM home_sections")
    fun clearHomeSections()


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addHomeSections(productEntity: HomeSectionEntity?)
}
