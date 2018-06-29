package com.projects.sebdeveloper6952.chapinleads.repos

import android.content.Context
import com.projects.sebdeveloper6952.chapinleads.models.*
import com.projects.sebdeveloper6952.chapinleads.room.AppDatabase
import io.reactivex.Single

class DataModel private constructor(context: Context) {

    /**
     * Room LeadDao
     */
    private val mLeadModel =
            AppDatabase.getInstance(context.applicationContext)?.leadsModel()!!
    /**
     * Room CategoryDao
     */
    private val mCategoryModel =
            AppDatabase.getInstance(context.applicationContext)?.categoriesModel()!!
    /**
     * Room LeadCategoryDao
     */
    private val mLeadCategoryModel =
            AppDatabase.getInstance(context.applicationContext)?.leadCategoryModel()!!

    /**
     * Inserts a new Lead into the leads table. The list of categories is also inserted into
     * the categories table. For each category LeadCategory object is inserted into the
     * lead_category table to save the relationship between a Lead and a Category.
     */
    fun insertLead(newLead: LeadModel, categories: List<String>): Single<Long> {
        return Single.fromCallable {
            mLeadModel.insert(newLead)
        }.doOnSuccess {
            for(cat in categories) {
                val category = mCategoryModel.getByTitle(cat)
                val catId = category?.id ?: mCategoryModel.insert(CategoryModel(cat))
                val leadId = it // lead id is the implicit parameter returned by previous Single
                mLeadCategoryModel.insert(LeadCategory(leadId, catId))
            }
        }
    }

    /**
     * Returns a rxjava Single with a list of all leads
     */
    fun getAllLeads(): Single<List<LeadModel>> {
        return Single.fromCallable { mLeadModel.getAll() }
    }

    fun getLeadsByIds(ids: List<Long>): Single<List<LeadModel>> {
        return Single.fromCallable {
            mLeadModel.getByIds(ids)
        }
    }

    /**
     * Returns a Lead object with all of its categories.
     */
    fun getLeadWithCategories(leadId: Long): Single<List<LeadWithCategories>> {
        return Single.fromCallable {
            mLeadCategoryModel.getLeadWithCategories(leadId)
        }
    }

    /**
     * Returns a category with a list of all the Leads associated with the specified category.
     */
    fun getCategoryWithLeads(catId: Long): Single<List<CategoryWithLeads>> {
        return Single.fromCallable {
            mLeadCategoryModel.getCategoryWithLeads(catId)
        }
    }

    fun getCategoriesWithLeads(catIds: List<Long>): Single<List<CategoryWithLeads>> {
        return Single.fromCallable {
            mLeadCategoryModel.getCategoryWithLeads(catIds)
        }
    }

    /**
     * Returns rxjava Single with the id of the newly inserted category.
     */
    fun insertCategory(newCategory: CategoryModel): Single<Long> {
        return Single.fromCallable {
            mCategoryModel.insert(newCategory)
        }
    }

    /**
     * Returns a list of all categories.
     */
    fun getAllCategories(): Single<List<CategoryModel>> {
        return Single.fromCallable {
            mCategoryModel.getAll()
        }
    }

    fun getCategoriesIdsByTitle(categories: List<String>): Single<List<Long>> {
        return Single.fromCallable {
            mCategoryModel.getIdsByTitle(categories)
        }
    }

    fun getLeadCategoryObject(id: Long): Single<LeadCategory> {
        return Single.fromCallable {
            mLeadCategoryModel.getById(id)
        }
    }

    fun getAllLeadCategoryObjects(): Single<List<LeadCategory>> {
        return Single.fromCallable {
            mLeadCategoryModel.getAll()
        }
    }

    fun getAllCategoryTitles(): Single<List<String>> {
        return Single.fromCallable {
            mCategoryModel.getAllTitles()
        }
    }

    /**
     * Singleton
     */
    companion object {

        private var instance: DataModel? = null

        fun getInstance(context: Context): DataModel {
            if(instance == null) {
                instance = DataModel(context)
            }
            return instance!!
        }
    }
}