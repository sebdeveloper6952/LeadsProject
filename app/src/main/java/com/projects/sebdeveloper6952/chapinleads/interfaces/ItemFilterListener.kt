package com.projects.sebdeveloper6952.chapinleads.interfaces

interface ItemFilterListener {
    fun onFilterSubmit(list: List<String>)
    // TODO("decide if this callback is necessary")
    fun onFilterCancel()
}