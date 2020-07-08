package com.leiming.roomdatabaselog.database.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LoggingEntity(
        @ColumnInfo(name = "relation_key")
        val relationKey: String,

        @ColumnInfo(name = "module_name")
        val moduleName: String?,

        @ColumnInfo(name = "module_color")
        val moduleColor: String?,

        @ColumnInfo(name = "extra_info")
        val extraInfo: String?,

        @Embedded
        val request: LoggingRequestEntity,

        @Embedded
        val responseEntity: LoggingResponseEntity?
) {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}