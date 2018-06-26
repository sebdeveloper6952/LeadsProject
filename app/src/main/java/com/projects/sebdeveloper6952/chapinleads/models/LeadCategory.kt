package com.projects.sebdeveloper6952.chapinleads.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "lead_category")
data class LeadCategory(
        @PrimaryKey(autoGenerate = true) val id: Long? = null,
        val leadId: Long?,
        val categoryId: Long?
) { @Ignore constructor(leadId: Long?, categoryId: Long?): this(null, leadId, categoryId)}