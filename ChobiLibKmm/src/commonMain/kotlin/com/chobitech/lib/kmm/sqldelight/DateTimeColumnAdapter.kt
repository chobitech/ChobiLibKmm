package com.chobitech.lib.kmm.sqldelight

import app.cash.sqldelight.ColumnAdapter
import com.chobitech.lib.kmm.DateTime

class DateTimeColumnAdapter : ColumnAdapter<DateTime, Long> {
    override fun decode(databaseValue: Long): DateTime {
        return DateTime(databaseValue)
    }

    override fun encode(value: DateTime): Long {
        return value.time
    }
}