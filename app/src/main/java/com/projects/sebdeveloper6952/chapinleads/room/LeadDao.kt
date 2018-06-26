package com.projects.sebdeveloper6952.chapinleads.room

import android.arch.persistence.room.*
import com.projects.sebdeveloper6952.chapinleads.dummy.DummyData.Lead
import com.projects.sebdeveloper6952.chapinleads.models.LeadModel

@Dao
interface LeadDao {

    @Query("SELECT * FROM leads")
    fun getAll(): List<LeadModel>

    @Query("SELECT * FROM leads WHERE id = :id LIMIT 1")
    fun getById(id: Int): LeadModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(newLead: LeadModel)
}