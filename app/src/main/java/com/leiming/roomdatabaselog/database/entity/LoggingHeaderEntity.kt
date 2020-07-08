package com.leiming.roomdatabaselog.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LoggingHeaderEntity(
    @ColumnInfo(name = "relation_key")
    val relationKey: String,

    @ColumnInfo(name = "header_name")
    val headerName: String,

    @ColumnInfo(name = "header_value")
    val headerValue: String?
) {
    @ColumnInfo(name = "header_id")
    @PrimaryKey(autoGenerate = true)
    var headerId: Long = 0
}