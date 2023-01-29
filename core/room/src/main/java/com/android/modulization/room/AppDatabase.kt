package com.android.modulization.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.android.modulization.data.local.entities.ItemCallEntity
import com.android.modulization.room.converter.BigDecimalTypeConverter
import com.android.modulization.room.dao.ItemToCallDao

@Database(entities = [ItemCallEntity::class], version = 1, exportSchema = false)
@TypeConverters(BigDecimalTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getItemCallDao(): ItemToCallDao

}