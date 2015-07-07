package org.soylentsystem.ormliteapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
import com.j256.ormlite.support.ConnectionSource
import com.j256.ormlite.table.TableUtils
import org.soylentsystem.ormliteapp.nestedpackage.Bar

class DatabaseHelper : OrmLiteSqliteOpenHelper {

    companion object {
        val DATABASE_NAME = "ormliteapp.sqlite"
        val DATABASE_VERSION = 1
    }

    constructor(context: Context) : super(context, DATABASE_NAME, null, DATABASE_VERSION)

    override fun onCreate(database: SQLiteDatabase, connectionSource: ConnectionSource) {
        TableUtils.createTable(connectionSource, javaClass<Foo>())
        TableUtils.createTable(connectionSource, javaClass<Bar>())
    }

    override fun onUpgrade(database: SQLiteDatabase?, connectionSource: ConnectionSource?, oldVersion: Int, newVersion: Int) {
        throw UnsupportedOperationException()
    }

}
