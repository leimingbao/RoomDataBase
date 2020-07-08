package com.leiming.roomdatabaselog.database.dao

import androidx.room.*
import com.leiming.roomdatabaselog.database.entity.LoggingHeaderEntity

@Dao
interface LoggingHeaderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLogging(vararg headers: LoggingHeaderEntity)
}