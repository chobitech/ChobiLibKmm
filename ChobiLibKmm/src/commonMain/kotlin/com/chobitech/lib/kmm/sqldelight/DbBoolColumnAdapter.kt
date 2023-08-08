package com.chobitech.lib.kmm.sqldelight

import app.cash.sqldelight.ColumnAdapter

class DbBoolColumnAdapter : ColumnAdapter<DbBool, Long> {

    override fun decode(databaseValue: Long): DbBool {
        return DbBool(databaseValue > 0L)
        //return databaseValue > 0L
    }

    override fun encode(value: DbBool): Long {
        return when (value.value) {
            true -> 1L
            false -> 0L
        }
    }
}