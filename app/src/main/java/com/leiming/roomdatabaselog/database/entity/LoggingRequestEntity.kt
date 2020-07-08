package com.leiming.roomdatabaselog.database.entity

import androidx.room.ColumnInfo

data class LoggingRequestEntity(
    @ColumnInfo(name = "request_time")
    val requestTime: Long,

    @ColumnInfo(name = "request_url")
    val url: String,

    @ColumnInfo(name = "request_method")
    val method: String
)