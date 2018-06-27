package com.projects.sebdeveloper6952.chapinleads.room

import android.arch.persistence.room.*
import com.projects.sebdeveloper6952.chapinleads.models.CategoryWithLeads
import com.projects.sebdeveloper6952.chapinleads.models.LeadCategory
import com.projects.sebdeveloper6952.chapinleads.models.LeadWithCategories

@Dao
interface LeadCategoryDao {

    @Query("SELECT * FROM lead_category")
    fun getAll(): List<LeadCategory>

    @Query("SELECT * FROM lead_category WHERE id = :id")
    fun getById(id: Long): LeadCategory

    @Query("SELECT * FROM lead_category WHERE leadId = :id")
    fun getByLeadId(id: Long): List<LeadCategory>

    @Query("SELECT * FROM lead_category WHERE categoryId = :id")
    fun getByCategoryId(id: Long): List<LeadCategory>

    @Query("SELECT * FROM leads WHERE id = :id")
    fun getLeadWithCategories(id: Long): List<LeadWithCategories>

    @Query("SELECT * FROM categories WHERE id = :id")
    fun getCategoryWithLeads(id: Long): List<CategoryWithLeads>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(leadCategory: LeadCategory):  Long

}