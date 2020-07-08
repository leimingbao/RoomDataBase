package com.leiming.roomdatabaselog.database.entity

import androidx.room.Embedded
import androidx.room.Relation
import com.leiming.roomdatabaselog.database.entity.LoggingEntity
import com.leiming.roomdatabaselog.database.entity.LoggingHeaderEntity

data class LoggingWithHeaderEntity(
        @Embedded
        val loggingEntity: LoggingEntity,

        @Relation(
                parentColumn = "relation_key",
                entityColumn = "relation_key"
        )
        val loggingHeaderEntity: MutableList<LoggingHeaderEntity>
)