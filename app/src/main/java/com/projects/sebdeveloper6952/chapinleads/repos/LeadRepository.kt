package com.projects.sebdeveloper6952.chapinleads.repos

import com.projects.sebdeveloper6952.chapinleads.models.LeadModel
import com.projects.sebdeveloper6952.chapinleads.room.LeadDao
import io.reactivex.Completable
import io.reactivex.Single

class LeadRepository(private val leadModel: LeadDao) {

    fun getAllLeads(): Single<List<LeadModel>> {
        return Single.fromCallable { leadModel.getAll() }
    }

    fun insertLead(newLead: LeadModel): Completable {
        return Completable.fromCallable { leadModel.insert(newLead) }
    }
}