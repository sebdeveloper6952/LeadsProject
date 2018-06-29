package com.projects.sebdeveloper6952.chapinleads.room

import android.arch.persistence.room.*
import com.projects.sebdeveloper6952.chapinleads.models.LeadModel

@Dao
interface LeadDao {

    @Query("SELECT * FROM leads")
    fun getAll(): List<LeadModel>

    @Query("SELECT * FROM leads WHERE id = :id LIMIT 1")
    fun getById(id: Long): LeadModel

    @Query("SELECT * FROM leads WHERE id IN(:ids)")
    fun getByIds(ids: List<Long>): List<LeadModel>

    @Query("SELECT * FROM leads WHERE title = :title")
    fun getByTitle(title: String): List<LeadModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(newLead: LeadModel): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(newLeads: List<LeadModel>): List<Long>
}