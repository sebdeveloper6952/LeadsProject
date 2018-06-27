package com.projects.sebdeveloper6952.chapinleads.models

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation

data class LeadWithCategories(@Embedded val lead: LeadModel) {
     @Relation(
             parentColumn = "id",
             entityColumn = "leadId",
             entity = LeadCategory::class,
             projection = ["categoryId"]
     ) var categoryIdList: List<Long>? = null
}