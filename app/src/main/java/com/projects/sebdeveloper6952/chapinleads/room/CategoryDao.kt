package com.projects.sebdeveloper6952.chapinleads.room

import android.arch.persistence.room.*
import com.projects.sebdeveloper6952.chapinleads.models.CategoryModel

@Dao
interface CategoryDao {

    @Query("SELECT * FROM categories")
    fun getAll(): List<CategoryModel>

    @Query("SELECT title FROM categories")
    fun getAllTitles(): List<String>

    @Query("SELECT * FROM categories WHERE id = :id")
    fun getById(id: Long): CategoryModel

    @Query("SELECT * FROM categories WHERE title = :title")
    fun getByTitle(title: String): CategoryModel

    @Query("SELECT id FROM categories WHERE title IN(:titles)")
    fun getIdsByTitle(titles: List<String>): List<Long>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(category: CategoryModel): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(categories: List<CategoryModel>): List<Long>

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun update(category: CategoryModel)
}