package org.soylentsystem.ormlite

import android.content.Context
import dalvik.system.DexFile

class Transaction(context: Context) : OrmLite(context) {

    override fun clean() {
        val packageName = context.getPackageName()
        val connectionSource = helper.javaClass.getMethod("getConnectionSource").invoke(helper)
        val clearTable = Class.forName("com.j256.ormlite.table.TableUtils").getMethods().last { method ->
            method.getName() == "clearTable"
        }

        DexFile(context.getPackageCodePath()).entries().asSequence().filter { klass ->
            klass.contains(packageName) && Class.forName(klass).getAnnotations().firstOrNull({ annotation ->
                annotation.toString().contains("DatabaseTable")
            }) != null
        }.forEach { klass ->
            clearTable.invoke(null, connectionSource, Class.forName(klass))
        }
    }

}
