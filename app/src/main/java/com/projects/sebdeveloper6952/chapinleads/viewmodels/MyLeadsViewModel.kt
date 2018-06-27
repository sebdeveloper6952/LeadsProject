package com.projects.sebdeveloper6952.chapinleads.viewmodels

import android.arch.lifecycle.ViewModel
import com.projects.sebdeveloper6952.chapinleads.models.CategoryModel
import com.projects.sebdeveloper6952.chapinleads.models.LeadModel
import com.projects.sebdeveloper6952.chapinleads.repos.DataModel
import io.reactivex.Single

class MyLeadsViewModel(private val mDataModel: DataModel): ViewModel() {


    fun addNewLead(lead: LeadModel, categories: List<String>): Single<Long> {
        return mDataModel.insertLead(lead, categories)
    }

    fun getAllLeads(): Single<List<LeadModel>> {
        return mDataModel.getAllLeads()
    }

    fun getAllCategories(): Single<List<CategoryModel>> {
        return mDataModel.getAllCategories()
    }
}