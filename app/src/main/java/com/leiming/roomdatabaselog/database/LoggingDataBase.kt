package com.leiming.roomdatabaselog.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.leiming.roomdatabaselog.database.dao.LoggingDao
import com.leiming.roomdatabaselog.database.dao.LoggingHeaderDao
import com.leiming.roomdatabaselog.database.dao.LoggingWithHeaderDao
import com.leiming.roomdatabaselog.database.entity.LoggingEntity
import com.leiming.roomdatabaselog.database.entity.LoggingHeaderEntity

@Database(
        entities = [LoggingEntity::class, LoggingHeaderEntity::class],
        version = 1,
        exportSchema = false
)
abstract class LoggingDataBase : RoomDatabase() {
    abstract fun getLoggingDao(): LoggingDao

    abstract fun getHeaderDao(): LoggingHeaderDao

    abstract fun getLoggingWithHeaderDao(): LoggingWithHeaderDao
}