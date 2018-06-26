package com.projects.sebdeveloper6952.chapinleads.models

import android.arch.persistence.room.*
import java.io.Serializable

@Entity(
        tableName = "leads"
)
data class LeadModel(
        @PrimaryKey(autoGenerate = true) var id: Long? = null,
        @ColumnInfo(name = "title") var title: String = "",
        @ColumnInfo(name = "details") var details: String = "",
        //@ColumnInfo(name = "categoryId") var categoryId: Int = -1,
        @ColumnInfo(name = "img_uri") var imgUri: String
): Serializable {
    @Ignore constructor(title: String,
                        details:String,
                        imgUri: String): this(null, title, details, imgUri)
}