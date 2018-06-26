package com.projects.sebdeveloper6952.chapinleads.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "categories")
data class CategoryModel(
        @PrimaryKey(autoGenerate = true) var id: Long? = null,
        var title: String
) { @Ignore constructor(title: String): this(null, title) }

object DummyCategories {
    var Cats = arrayOf(
            "Programación",
            "Matemática",
            "Finanzas",
            "Psicología",
            "Deportes",
            "Publicidad"
    )
}