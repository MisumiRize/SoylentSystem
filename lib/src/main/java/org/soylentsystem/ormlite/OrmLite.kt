package org.soylentsystem.ormlite

import android.content.Context
import org.soylentsystem.Strategy

abstract class OrmLite(val context: Context) : Strategy {

    val helper: Any

    init {
        helper = Class.forName("com.j256.ormlite.android.apptools.OpenHelperManager")
                .getMethod("getHelper", javaClass<Context>())
                .invoke(null, context)
    }

}
