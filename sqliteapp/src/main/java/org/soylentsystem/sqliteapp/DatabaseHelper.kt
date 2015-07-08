package org.soylentsystem.sqliteapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper : SQLiteOpenHelper {

    companion object {
        val DATABASE_NAME = "sqliteapp.sqlite"
        val DATABASE_VERSION = 1
    }

    constructor(context: Context) : super(context, DATABASE_NAME, null, DATABASE_VERSION)

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table FOO(id INTEGER PRIMARY KEY, name TEXT NOT NULL)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        throw UnsupportedOperationException()
    }

}
