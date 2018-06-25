package com.projects.sebdeveloper6952.chapinleads.dummy

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Ignore
import android.net.Uri
import com.projects.sebdeveloper6952.chapinleads.R
import java.io.Serializable

// TODO("remove for release")
object DummyData {

    var LEADS: ArrayList<Lead>
    var RECS: ArrayList<Lead>
    var CATEGORIES: Array<String>

    init {
        LEADS = arrayListOf(
                Lead(0, "Lead1", "Lead1 details...", "Tecnología" , "uri_missing"),
                Lead(0, "Lead2", "Lead2 details...", "Matemática" , "uri_missing"),
                Lead(0, "Lead3", "Lead3 details...", "Publicidad" , "uri_missing"),
                Lead(0, "Lead4", "Lead4 details...", "Deportes" , "uri_missing"),
                Lead(0, "Lead5", "Lead5 details...", "Tecnología" , "uri_missing"),
                Lead(0, "Lead6", "Lead6 details...", "Finanzas" , "uri_missing")
        )
        RECS = arrayListOf(
                Lead(0,"Nvidia", "American technology company. Designs GPUs", "Tecnología" ,"uri_missing"),
                Lead(0,"Recommendation2", "Recommendation2 details...", "Matemática" ,"uri_missing"),
                Lead(0,"Recommendation3", "Recommendation3 details...", "Deportes" ,"uri_missing"),
                Lead(0,"Recommendation4", "Recommendation4 details...", "Publicidad" ,"uri_missing"),
                Lead(0,"Recommendation5", "Recommendation5 details...", "Deportes" ,"uri_missing"),
                Lead(0,"Recommendation6", "Recommendation6 details...", "Finanzas" ,"uri_missing")
        )

        CATEGORIES = arrayOf(
                "Tecnología", "Deportes", "Psicología", "Finanzas",
                "Matemática", "Marketing", "Publicidad", "Serigrafía"
        )
    }

    data class ItemLead(
            val title: String,
            val details: String,
            val category: String,
            val imgId: Int
    ): Serializable

    // test class for Room
    @Entity(tableName = "leads")
    data class Lead(
            @ColumnInfo(name = "id")
            @PrimaryKey(autoGenerate = true) var id: Int = 0,
            @ColumnInfo(name = "title") var title: String = "",
            @ColumnInfo(name = "details") var details: String = "",
            @ColumnInfo(name = "category") var category: String = "",
            @ColumnInfo(name = "img_uri") var imgUri: String
    ): Serializable { @Ignore constructor(): this(0, "", "", "", "") }
}