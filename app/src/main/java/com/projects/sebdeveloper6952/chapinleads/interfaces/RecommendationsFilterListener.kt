package com.projects.sebdeveloper6952.chapinleads.interfaces

import com.projects.sebdeveloper6952.chapinleads.dummy.DummyData

interface RecommendationsFilterListener {
    fun onSubmit(list: List<String>)
    fun onCancel()
}