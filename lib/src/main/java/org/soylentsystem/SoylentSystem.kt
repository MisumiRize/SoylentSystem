package org.soylentsystem

import android.content.Context

class SoylentSystem {

    companion object {

        fun clean(context: Context) {
            AutoLoader.loadOrm().createStrategy(context).clean()
        }

    }

}
