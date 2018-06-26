package com.projects.sebdeveloper6952.chapinleads.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "leads")
data class LeadModel(
        @ColumnInfo(name = "id")
        @PrimaryKey(autoGenerate = true) var id: Int = 0,
        @ColumnInfo(name = "title") var title: String = "",
        @ColumnInfo(name = "details") var details: String = "",
        @ColumnInfo(name = "category") var category: String = "",
        @ColumnInfo(name = "img_uri") var imgUri: String
): Serializable { @Ignore constructor(): this(0, "", "", "", "") }