package org.soylentsystem.realm

import android.content.Context
import org.soylentsystem.Strategy

abstract class Realm(val context: Context) : Strategy {

    val realm: Any

    init {
        realm = Class.forName("io.realm.Realm")
                .getMethod("getInstance", javaClass<Context>())
                .invoke(null, context)
    }

}
