package org.soylentsystem.ormliteapp

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

DatabaseTable class Foo {

    DatabaseField(id = true) private var id: Int = 0

    DatabaseField private var name: String = ""

    private constructor()

    public constructor(id: Int, name: String) {
        this.id = id
        this.name = name
    }

}
