package com.projects.sebdeveloper6952.chapinleads.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.projects.sebdeveloper6952.chapinleads.models.LeadCategory

@Dao
interface LeadCategoryDao {

    @Query("SELECT * FROM lead_category")
    fun getAll(): List<LeadCategory>

    @Query("SELECT * FROM lead_category WHERE id = :id")
    fun getById(id: Long): LeadCategory

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(leadCategory: LeadCategory):  Long

}