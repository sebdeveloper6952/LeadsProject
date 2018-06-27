package com.projects.sebdeveloper6952.chapinleads.repos

import com.projects.sebdeveloper6952.chapinleads.models.CategoryModel
import com.projects.sebdeveloper6952.chapinleads.room.CategoryDao
import io.reactivex.Single

class CategoryRepository(private val mCategoryModel: CategoryDao) {

    fun getAllCategories(): Single<List<CategoryModel>> {
        return Single.fromCallable { mCategoryModel.getAll() }
    }

    fun insert(category: CategoryModel): Single<Long> {
        return Single.fromCallable { mCategoryModel.insert(category) }
    }

    fun insertCategories(categories: List<CategoryModel>): Single<List<Long>> {
        return Single.fromCallable { mCategoryModel.insert(categories) }
    }
}