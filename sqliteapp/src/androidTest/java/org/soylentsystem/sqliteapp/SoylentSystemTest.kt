package org.soylentsystem.sqliteapp

import android.content.ContentValues
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.test.suitebuilder.annotation.LargeTest
import org.junit.Test
import org.junit.runner.RunWith
import org.soylentsystem.SoylentSystem
import kotlin.test.assertEquals

LargeTest RunWith(AndroidJUnit4::class) class SoylentSystemTest {

    Test fun cleanWillDeleteAllData() {
        val db = DatabaseHelper(InstrumentationRegistry.getTargetContext()).getWritableDatabase()

        val values = ContentValues()
        values.put("name", "test")
        db.insert("FOO", null, values)

        val dataSet1 = db.query("FOO", arrayOf("count(*)"), null, null, null, null, null)
        dataSet1.moveToFirst()
        assertEquals(dataSet1.getInt(0), 1)
        dataSet1.close()

        SoylentSystem.clean(InstrumentationRegistry.getTargetContext())

        val dataSet2 = db.query("FOO", arrayOf("count(*)"), null, null, null, null, null)
        dataSet2.moveToFirst()
        assertEquals(dataSet2.getInt(0), 0)
        dataSet2.close()
    }

}
