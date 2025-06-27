package com.alireza.brochure.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alireza.brochure.database.entity.SuperBannerEntity

@Dao
interface SuperBannerDao {
    @Query("SELECT * FROM super_banners ORDER BY order_index ASC")
    suspend fun getAll(): List<SuperBannerEntity>

    @Query("SELECT * FROM super_banners WHERE group_id = :groupId")
    suspend fun getByGroup(groupId: String): List<SuperBannerEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(banners: List<SuperBannerEntity>)
}