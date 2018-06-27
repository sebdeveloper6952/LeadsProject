package com.projects.sebdeveloper6952.chapinleads.repos

import android.arch.persistence.room.OnConflictStrategy
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

    // TODO("decide if repositories are necessary")
    /**
     * Lead and category repositories.
     */
    private val mLeadRepo = LeadRepository(mLeadModel)
    private val mCategoryRepo = CategoryRepository(mCategoryModel)


    /**
     * Inserts a new Lead into the leads table. The list of categories is also inserted into
     * the categories table. For each category LeadCategory object is inserted into the
     * lead_category table to save the relationship between a Lead and a Category.
     */
    fun insertLead(newLead: LeadModel, categories: List<String>): Single<Long> {
        return Single.fromCallable {
            mLeadRepo.insertLead(newLead)
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

    /**
     * Returns a Lead object with all of its categories.
     */
    fun getLeadWithCategories(id: Long): Single<List<LeadWithCategories>> {
        return Single.fromCallable {
            mLeadCategoryModel.getLeadWithCategories(id)
        }
    }

    /**
     * Returns a category with a list of all the Leads associated with the specified category.
     */
    fun getCategoryWithLeads(id: Long): Single<List<CategoryWithLeads>> {
        return Single.fromCallable {
            mLeadCategoryModel.getCategoryWithLeads(id)
        }
    }

    /**
     * Returns rxjava Single with the id of the newly inserted category.
     */
    fun insertCategory(newCategory: CategoryModel) = mCategoryRepo.insert(newCategory)

    /**
     * Returns a list of all categories.
     */
    fun getAllCategories(): Single<List<CategoryModel>> {
        return Single.fromCallable { mCategoryModel.getAll() }
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