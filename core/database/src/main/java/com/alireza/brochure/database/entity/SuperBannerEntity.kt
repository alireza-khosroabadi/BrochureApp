package com.alireza.brochure.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "super_banners")
data class SuperBannerEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "published_from") val publishedFrom: String,
    @ColumnInfo(name = "published_until") val publishedUntil: String,
    @ColumnInfo(name = "click_url") val clickUrl: String,
    @ColumnInfo(name = "image_url") val imageUrl: String,
    @ColumnInfo(name = "order_index") val orderIndex: Int,
    @ColumnInfo(name = "group_id") val groupId: String,
)