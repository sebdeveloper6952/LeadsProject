package com.projects.sebdeveloper6952.chapinleads.room

import android.arch.persistence.room.*
import com.projects.sebdeveloper6952.chapinleads.dummy.DummyData.Lead

@Dao
interface ItemDao {

    @Query("SELECT * FROM leads")
    fun getAll(): List<Lead>

    @Query("SELECT * FROM leads WHERE id = :id LIMIT 1")
    fun getById(id: Long): Lead

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(newLead: Lead)

    @Delete
    fun deleteById(id: Long)
}