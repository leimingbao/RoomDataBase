package com.leiming.roomdatabaselog.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.leiming.roomdatabaselog.database.entity.LoggingWithHeaderEntity

@Dao
interface LoggingWithHeaderDao {
    @Transaction
    @Query("SELECT * FROM LoggingEntity")
    suspend fun loadAllWithHeader(): MutableList<LoggingWithHeaderEntity>
}