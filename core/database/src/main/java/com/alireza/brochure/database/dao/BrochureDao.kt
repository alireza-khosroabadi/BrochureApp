package com.alireza.brochure.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.alireza.brochure.database.entity.BrochureEntity

@Dao
internal interface BrochureDao {

    @Upsert
    suspend fun insert(entity: BrochureEntity)

    @Upsert
    suspend fun insertAll(brochureList: List<BrochureEntity>)

    @Query("SELECT * FROM BrochureEntity ORDER BY order_index ASC")
    suspend fun getAllBrochure(): List<BrochureEntity>

    @Query("SELECT * FROM BrochureEntity WHERE _id =:brochureId")
    suspend fun findBrochureById(brochureId: String): BrochureEntity

    @Query("DELETE FROM BrochureEntity")
    suspend fun clearTable()
}