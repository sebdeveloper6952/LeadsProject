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

    var LEADS: ArrayList<ItemLead>
    var RECS: ArrayList<ItemLead>
    var CATEGORIES: Array<String>

    init {
        LEADS = arrayListOf(
                ItemLead("Lead1", "Lead1 details...", "Tecnología" ,R.drawable.ic_image_black_24dp),
                ItemLead("Lead2", "Lead2 details...", "Matemática" ,R.drawable.ic_image_black_24dp),
                ItemLead("Lead3", "Lead3 details...", "Publicidad" ,R.drawable.ic_image_black_24dp),
                ItemLead("Lead4", "Lead4 details...", "Deportes" ,R.drawable.ic_image_black_24dp),
                ItemLead("Lead5", "Lead5 details...", "Tecnología" ,R.drawable.ic_image_black_24dp),
                ItemLead("Lead6", "Lead6 details...", "Finanzas" ,R.drawable.ic_image_black_24dp)
        )
        RECS = arrayListOf(
                ItemLead("Nvidia", "American technology company. Designs GPUs", "Tecnología" ,R.drawable.ic_image_black_24dp),
                ItemLead("Recommendation2", "Recommendation2 details...", "Matemática" ,R.drawable.ic_image_black_24dp),
                ItemLead("Recommendation3", "Recommendation3 details...", "Deportes" ,R.drawable.ic_image_black_24dp),
                ItemLead("Recommendation4", "Recommendation4 details...", "Publicidad" ,R.drawable.ic_image_black_24dp),
                ItemLead("Recommendation5", "Recommendation5 details...", "Deportes" ,R.drawable.ic_image_black_24dp),
                ItemLead("Recommendation6", "Recommendation6 details...", "Finanzas" ,R.drawable.ic_image_black_24dp)
        )

        CATEGORIES = arrayOf(
                "Tecnología", "Deportes", "Psicología", "Finanzas",
                "Matemática", "Marketing", "Publicidad", "Serigrafía"
        )
    }

    fun addLead(newLead: ItemLead) = LEADS.add(newLead)
    fun addRecommendation(newRec: ItemLead) = RECS.add(newRec)

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