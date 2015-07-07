package org.soylentsystem.realmapp

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.test.suitebuilder.annotation.LargeTest
import io.realm.Realm
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.soylentsystem.SoylentSystem
import org.soylentsystem.realmapp.nestedpackage.Bar
import kotlin.test.assertEquals

LargeTest RunWith(AndroidJUnit4::class) class SoylentSystemTest {

    var context: Context? = null
    var realm: Realm? = null

    Before fun setUp() {
        context = InstrumentationRegistry.getTargetContext()
        realm = Realm.getInstance(context)
    }

    Test fun cleanWillDeleteAllDataAllocatedOnPackageRoot() {
        realm!!.beginTransaction()

        val foo1 = realm!!.createObject(javaClass<Foo>())
        foo1.setId(123)
        foo1.setName("test")

        val foo2 = realm!!.createObject(javaClass<Foo>())
        foo2.setId(456)
        foo2.setName("test")

        realm!!.commitTransaction()
        assertEquals(realm!!.allObjects(javaClass<Foo>()).size(), 2)

        SoylentSystem.clean(context!!)
        assertEquals(realm!!.allObjects(javaClass<Foo>()).size(), 0)
    }

    Test fun cleanWillDeleteAllDataAllocatedOnNestedPackage() {
        realm!!.beginTransaction()

        val bar1 = realm!!.createObject(javaClass<Bar>())
        bar1.setId(123)
        bar1.setName("test")

        val bar2 = realm!!.createObject(javaClass<Bar>())
        bar2.setId(456)
        bar2.setName("test")

        realm!!.commitTransaction()
        assertEquals(realm!!.allObjects(javaClass<Bar>()).size(), 2)

        SoylentSystem.clean(context!!)
        assertEquals(realm!!.allObjects(javaClass<Bar>()).size(), 0)
    }

    After fun closeRealm() {
        realm!!.close()
    }

}
