package com.projects.sebdeveloper6952.chapinleads.viewmodels

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.projects.sebdeveloper6952.chapinleads.repos.DataModel

class MyLeadsViewModelFactory(private val mDataModel: DataModel): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(DataModel::class.java).newInstance(mDataModel)
    }
}