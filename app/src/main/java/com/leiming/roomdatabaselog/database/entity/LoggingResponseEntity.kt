package com.leiming.roomdatabaselog.database.entity

import androidx.room.ColumnInfo

data class LoggingResponseEntity(
    @ColumnInfo(name = "response_time")
    val responseTime: Long,

    @ColumnInfo(name = "response_code")
    val responseCode: Int,

    @ColumnInfo(name = "response_body")
    val responseBody: String?
)