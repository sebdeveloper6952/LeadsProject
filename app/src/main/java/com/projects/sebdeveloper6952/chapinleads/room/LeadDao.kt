package com.projects.sebdeveloper6952.chapinleads.room

import android.arch.persistence.room.*
import com.projects.sebdeveloper6952.chapinleads.dummy.DummyData.Lead
import io.reactivex.Flowable
import io.reactivex.Maybe

@Dao
interface LeadDao {

    @Query("SELECT * FROM leads")
    fun getAll(): Flowable<List<Lead>>

    @Query("SELECT * FROM leads WHERE id = :id LIMIT 1")
    fun getById(id: Int): Maybe<Lead>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(newLead: Lead)
}