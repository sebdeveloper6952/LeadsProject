package com.projects.sebdeveloper6952.chapinleads.models

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation

data class LeadWithCategories(
     @Embedded val lead: LeadModel,
     @Relation(
             parentColumn = "id",
             entityColumn = "lead_id",
             entity = LeadCategory::class,
             projection = ["categoryId"]
     ) val categoryIdList: List<Long>
)