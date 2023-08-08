package com.chobitech.lib.kmm

import androidx.sqlite.db.SupportSQLiteDatabase
import app.cash.sqldelight.db.AfterVersion
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlSchema
import app.cash.sqldelight.driver.android.AndroidSqliteDriver

open class ChobiAndroidSqliteDriverCallback(
    scheme: SqlSchema<QueryResult.Value<Unit>>,
    vararg callbacks: AfterVersion
) : AndroidSqliteDriver.Callback(scheme, *callbacks) {

    override fun onConfigure(db: SupportSQLiteDatabase) {
        super.onConfigure(db)
        setPragma(db, "JOURNAL_MODE = WAL")
        setPragma(db, "SYNCHRONOUS = 2")
    }

    private fun setPragma(db: SupportSQLiteDatabase, pragma: String) {
        db.query("PRAGMA $pragma").use {
            it.moveToFirst()
        }
    }
}