package com.projects.sebdeveloper6952.chapinleads.repos

import com.projects.sebdeveloper6952.chapinleads.models.CategoryModel
import com.projects.sebdeveloper6952.chapinleads.room.CategoryDao
import io.reactivex.Completable
import io.reactivex.Single

class CategoryRepository(private val mCategoryModel: CategoryDao) {

    fun getAllCategories(): Single<List<CategoryModel>> {
        return Single.fromCallable { mCategoryModel.getAll() }
    }

    fun insertCategories(categories: List<CategoryModel>): Completable {
        return Completable.fromCallable { mCategoryModel.insert(categories) }
    }
}