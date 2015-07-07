package org.soylentsystem

import android.content.Context
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
        throw RuntimeException("orm not found")
    }

    enum class Orm(val klass: String) {
        ORMLITE("com.j256.ormlite.android.apptools.OpenHelperManager") {
            override fun createStrategy(context: Context) = OrmLiteTransaction(context)
        },
        REALM("io.realm.Realm") {
            override fun createStrategy(context: Context) = RealmTransaction(context)
        };

        abstract fun createStrategy(context: Context): Strategy
    }

}
