package com.projects.sebdeveloper6952.chapinleads.models

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation


data class CategoryWithLeads(
        @Embedded val category: CategoryModel,
        @Relation(
                parentColumn = "id",
                entityColumn = "",
                entity = LeadCategory::class,
                projection = ["leadId"]
        ) val leadIdList: List<Long>
)