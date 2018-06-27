package com.projects.sebdeveloper6952.chapinleads.repos

import com.projects.sebdeveloper6952.chapinleads.models.LeadModel
import com.projects.sebdeveloper6952.chapinleads.room.LeadDao
import io.reactivex.Completable
import io.reactivex.Single

class LeadRepository(private val mLeadModel: LeadDao) {

    fun getAllLeads() = mLeadModel.getAll()

    fun insertLead(newLead: LeadModel) = mLeadModel.insert(newLead)

    fun insertAll(newLeads: List<LeadModel>) = mLeadModel.insert(newLeads)
}