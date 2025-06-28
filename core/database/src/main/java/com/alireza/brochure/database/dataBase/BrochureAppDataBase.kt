package com.alireza.brochure.database.dataBase

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alireza.brochure.database.dao.BrochureDao
import com.alireza.brochure.database.dao.SuperBannerDao
import com.alireza.brochure.database.entity.BrochureEntity
import com.alireza.brochure.database.entity.SuperBannerEntity
import java.util.concurrent.Executors

@Database(entities = [BrochureEntity::class, SuperBannerEntity::class], version = 2, exportSchema = false)
internal abstract class BrochureAppDataBase : RoomDatabase() {
    internal abstract fun brochureDao(): BrochureDao
    internal abstract fun superBannerDao(): SuperBannerDao

    companion object {
        @Volatile
        private var INSTANCE: BrochureAppDataBase? = null

        fun getDataBase(context: Context): BrochureAppDataBase {
            return INSTANCE ?: synchronized(this) {
                val builder = Room.databaseBuilder(
                    context.applicationContext,
                    BrochureAppDataBase::class.java,
                    "brochure-db"
                )
                    .fallbackToDestructiveMigration()
                val instance = builder.build()
                    INSTANCE = instance
                instance
            }
        }
    }
}