package com.leiming.roomdatabaselog.database.dao

import androidx.room.*
import com.leiming.roomdatabaselog.database.entity.LoggingEntity

@Dao
interface LoggingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLogging(vararg logs: LoggingEntity)

    @Delete
    suspend fun deleteLogging(vararg logs: LoggingEntity)

    @Query("DELETE FROM LoggingEntity")
    suspend fun deleteAllLogging()

    @Query("SELECT * FROM LoggingEntity")
    suspend fun loadAllLogging(): MutableList<LoggingEntity>

    @Query("DELETE FROM LoggingEntity WHERE request_time<:timeStamp")
    suspend fun delete24HoursAway(timeStamp: String)
}