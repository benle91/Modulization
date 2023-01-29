package com.android.modulization.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.modulization.data.local.entities.ItemCallEntity

@Dao
interface ItemToCallDao {

    @Query("SELECT * FROM ItemCall")
    suspend fun getAll(): List<ItemCallEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg items: ItemCallEntity): List<Long>

}