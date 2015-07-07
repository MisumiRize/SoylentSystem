package org.soylentsystem

import android.content.Context
import org.soylentsystem.lib.realm.Transaction

class SoylentSystem {

    companion object {

        fun clean(context: Context) {
            AutoLoader.loadOrm().createStrategy(context).clean()
        }

    }

}
