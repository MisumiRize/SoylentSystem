package org.soylentsystem.ormliteapp

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.test.suitebuilder.annotation.LargeTest
import com.j256.ormlite.dao.Dao
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.soylentsystem.SoylentSystem
import org.soylentsystem.ormliteapp.nestedpackage.Bar
import kotlin.test.assertEquals

LargeTest RunWith(AndroidJUnit4::class) class SoylentSystemTest {

    val activityRule = ActivityTestRule(javaClass<MainActivity>())

    Rule public fun getRule(): ActivityTestRule<MainActivity> = activityRule

    Test fun cleanWillDeleteAllDataAllocatedOnPackageRoot() {
        val dao: Dao<Foo, Int> = activityRule.getActivity().getHelper().getDao(javaClass<Foo>())
        dao.create(Foo(1, "test"))
        dao.create(Foo(2, "test"))
        assertEquals(2L, dao.countOf())

        SoylentSystem.clean(activityRule.getActivity())
        assertEquals(0L, dao.countOf())
    }

    Test fun cleanWillDeleteAllDataAllocatedOnNestedPackage() {
        val dao: Dao<Bar, Int> = activityRule.getActivity().getHelper().getDao(javaClass<Bar>())
        dao.create(Bar(1, "test"))
        dao.create(Bar(2, "test"))
        assertEquals(2L, dao.countOf())

        SoylentSystem.clean(activityRule.getActivity())
        assertEquals(0L, dao.countOf())
    }

}
