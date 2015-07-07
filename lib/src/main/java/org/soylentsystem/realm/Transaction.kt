package org.soylentsystem.realm

import android.content.Context
import dalvik.system.DexFile

class Transaction(context: Context) : Realm(context) {

    override fun clean() {
        val realmClass = realm.javaClass
        val realmObjectClass = Class.forName("io.realm.RealmObject")
        var packageName = context.getPackageName()

        realmClass.getMethod("beginTransaction").invoke(realm)

        DexFile(context.getPackageCodePath()).entries().asSequence().filter { klass ->
            klass.contains(packageName) && realmObjectClass.isAssignableFrom(Class.forName(klass))
        }.forEach { klass ->
            realmClass.getMethods().first { m -> m.getName() == "clear" }.invoke(realm, Class.forName(klass))
        }

        realmClass.getMethod("commitTransaction").invoke(realm)
        realmClass.getMethod("close").invoke(realm)
    }

}
