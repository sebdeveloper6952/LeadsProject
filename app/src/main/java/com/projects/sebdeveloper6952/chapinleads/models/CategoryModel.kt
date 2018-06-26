package com.projects.sebdeveloper6952.chapinleads.models

data class CategoryModel(
        var title: String
)

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