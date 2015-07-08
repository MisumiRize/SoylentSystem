package org.soylentsystem.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import dalvik.system.DexFile
import org.soylentsystem.Strategy

abstract class Sqlite(val context: Context) : Strategy {

    val db: SQLiteDatabase

    init {
        val helperClassName = DexFile(context.getPackageCodePath()).entries().asSequence().first { className ->
            try {
                val klass = Class.forName(className)
                javaClass<SQLiteOpenHelper>().isAssignableFrom(klass)
            }
            catch (ex: NoClassDefFoundError) {
                false
            }
            catch (ex: ClassNotFoundException) {
                false
            }
        }
        val helperClass = Class.forName(helperClassName)
        val helper = helperClass.getDeclaredConstructor(javaClass<Context>())
                .newInstance(context)
        db = helperClass.getMethod("getWritableDatabase")
                .invoke(helper) as SQLiteDatabase
    }

}
