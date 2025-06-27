package com.alireza.brochure.database.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BrochureEntity(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "_id") val contentId: String,
    val title: String,
    val distance: Double,
    val type: String,
    @ColumnInfo(name = "image_url") val imageUrl: String,
    @ColumnInfo(name = "published_from") val publishedFrom: String,
    @ColumnInfo(name = "published_until") val publishedUntil: String,
    @ColumnInfo(name = "valid_from") val validFrom: String,
    @ColumnInfo(name = "valid_until") val validUntil: String,
    @ColumnInfo(name = "order_index") val orderIndex: Int,
    @Embedded(prefix = "store_") val storeLocation: StoreLocationEntity
)
