package com.projects.sebdeveloper6952.chapinleads.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.projects.sebdeveloper6952.chapinleads.models.CategoryModel

@Dao
interface CategoryDao {

    @Query("SELECT * FROM categories")
    fun getAll(): List<CategoryModel>

    @Query("SELECT * FROM categories WHERE id = :id")
    fun getById(id: Long): CategoryModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(newCat: CategoryModel): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(categories: List<CategoryModel>): List<Long>

}