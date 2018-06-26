package com.projects.sebdeveloper6952.chapinleads.repos

import android.content.Context
import com.projects.sebdeveloper6952.chapinleads.models.CategoryModel
import com.projects.sebdeveloper6952.chapinleads.models.LeadCategory
import com.projects.sebdeveloper6952.chapinleads.models.LeadModel
import com.projects.sebdeveloper6952.chapinleads.room.AppDatabase
import io.reactivex.Completable

class DataModel(context: Context) {

    private val mLeadModel =
            AppDatabase.getInstance(context.applicationContext)?.leadsModel()!!
    private val mCategoryModel =
            AppDatabase.getInstance(context.applicationContext)?.categoriesModel()!!
    private val mLeadCategoryModel =
            AppDatabase.getInstance(context.applicationContext)?.leadCategoryModel()!!

    private val mLeadRepo = LeadRepository(mLeadModel)
    private val mCategoryRepo = CategoryRepository(mCategoryModel)

    fun insertLead(newLead: LeadModel, categories: List<String>): Completable {
        for(cat in categories) {
            val category = CategoryModel(cat)
            mCategoryModel.insert(category)
            mLeadCategoryModel.insert(LeadCategory(newLead.id, category.id))
        }
        return mLeadRepo.insertLead(newLead)
    }

    fun getAllLeads() = mLeadRepo.getAllLeads()

    fun getAllCategories() = mCategoryRepo.getAllCategories()
}