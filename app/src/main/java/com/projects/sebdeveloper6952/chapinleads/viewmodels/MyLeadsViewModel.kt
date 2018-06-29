package com.projects.sebdeveloper6952.chapinleads.viewmodels

import android.arch.lifecycle.ViewModel
import com.projects.sebdeveloper6952.chapinleads.models.CategoryModel
import com.projects.sebdeveloper6952.chapinleads.models.CategoryWithLeads
import com.projects.sebdeveloper6952.chapinleads.models.LeadModel
import com.projects.sebdeveloper6952.chapinleads.repos.DataModel
import io.reactivex.Single

class MyLeadsViewModel(private val mDataModel: DataModel): ViewModel() {

    fun addNewLead(lead: LeadModel, categories: List<String>) =
            mDataModel.insertLead(lead, categories)

    fun addNewCategory(category: CategoryModel) = mDataModel.insertCategory(category)

    fun getAllLeads() = mDataModel.getAllLeads()

    fun getAllCategories() = mDataModel.getAllCategories()

    fun getAllCategoryTitles() = mDataModel.getAllCategoryTitles()

    fun getLeadsForCategories(categories: List<String>): Single<List<LeadModel>> {
        return mDataModel.getCategoriesIdsByTitle(categories)
                .flatMap {
                    mDataModel.getCategoriesWithLeads(it)
                }
                .map {
                    getIds(it)
                }
                .flatMap {
                    mDataModel.getLeadsByIds(it)
                }
                .map { it }
    }

    private fun getIds(list: List<CategoryWithLeads>): List<Long> {
        val ids = ArrayList<Long>()
        for(cat in list) {
            for(id in cat.leadIdList!!) {
                ids.add(id)
            }
        }
        return ids
    }
}