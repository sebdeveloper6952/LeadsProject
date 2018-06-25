package com.projects.sebdeveloper6952.chapinleads.repos

import com.projects.sebdeveloper6952.chapinleads.dummy.DummyData
import com.projects.sebdeveloper6952.chapinleads.room.LeadDao
import io.reactivex.Completable
import io.reactivex.Single

class LeadRepository(private val itemModel: LeadDao) {

    fun getAllItems(): Single<List<DummyData.Lead>> {
        return Single.fromCallable { itemModel.getAll() }
    }

    fun insertItem(newLead: DummyData.Lead): Completable {
        return Completable.fromCallable { itemModel.insert(newLead) }
    }
}