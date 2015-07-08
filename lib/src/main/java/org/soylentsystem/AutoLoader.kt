package org.soylentsystem

import android.content.Context
import org.soylentsystem.sqlite.Transaction as SqliteTransaction
import org.soylentsystem.ormlite.Transaction as OrmLiteTransaction
import org.soylentsystem.realm.Transaction as RealmTransaction

object AutoLoader {

    fun loadOrm(): Orm {
        Orm.values().forEach { orm ->
            try {
                Class.forName(orm.klass)
                return orm
            }
            catch (ex: ClassNotFoundException) { }
        }
        return Orm.SQLITE
    }

    enum class Orm(val klass: String) {
        ORMLITE("com.j256.ormlite.android.apptools.OpenHelperManager") {
            override fun createStrategy(context: Context) = OrmLiteTransaction(context)
        },
        REALM("io.realm.Realm") {
            override fun createStrategy(context: Context) = RealmTransaction(context)
        },
        SQLITE("") {
            override fun createStrategy(context: Context) = SqliteTransaction(context)
        };

        abstract fun createStrategy(context: Context): Strategy
    }

}
