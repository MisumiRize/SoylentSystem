package org.soylentsystem.sqlite

import android.content.Context

class Transaction(context: Context) : Sqlite(context) {

    override fun clean() {
        val tables = db.query("sqlite_master", arrayOf("name"), null, null, null, null, null)
        tables.moveToFirst()

        db.beginTransaction()
        do {
            db.delete(tables.getString(0), null, null)
        } while (tables.moveToNext())
        tables.close()
        db.setTransactionSuccessful()

        db.endTransaction()
        db.execSQL("vacuum")
    }

}
