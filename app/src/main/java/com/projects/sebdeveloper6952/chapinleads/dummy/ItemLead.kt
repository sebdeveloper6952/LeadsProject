package com.projects.sebdeveloper6952.chapinleads.dummy

import com.projects.sebdeveloper6952.chapinleads.R
import java.io.Serializable

// TODO("remove for release")
object DummyData {

    var LEADS: ArrayList<ItemLead>
    var RECS: ArrayList<ItemLead>

    init {
        LEADS = arrayListOf(
                ItemLead("Lead1", "Lead1 details...", "category" ,R.drawable.ic_image_black_24dp),
                ItemLead("Lead2", "Lead2 details...", "category" ,R.drawable.ic_image_black_24dp),
                ItemLead("Lead3", "Lead3 details...", "category" ,R.drawable.ic_image_black_24dp),
                ItemLead("Lead4", "Lead4 details...", "category" ,R.drawable.ic_image_black_24dp),
                ItemLead("Lead5", "Lead5 details...", "category" ,R.drawable.ic_image_black_24dp),
                ItemLead("Lead6", "Lead6 details...", "category" ,R.drawable.ic_image_black_24dp)
        )
        RECS = arrayListOf(
                ItemLead("nVidia", "American technology company. Designs GPUs", "Technology" ,R.drawable.ic_image_black_24dp),
                ItemLead("Recommendation2", "Recommendation2 details...", "category" ,R.drawable.ic_image_black_24dp),
                ItemLead("Recommendation3", "Recommendation3 details...", "category" ,R.drawable.ic_image_black_24dp),
                ItemLead("Recommendation4", "Recommendation4 details...", "category" ,R.drawable.ic_image_black_24dp),
                ItemLead("Recommendation5", "Recommendation5 details...", "category" ,R.drawable.ic_image_black_24dp),
                ItemLead("Recommendation6", "Recommendation6 details...", "category" ,R.drawable.ic_image_black_24dp)
        )
    }

    fun addLead(newLead: ItemLead) = LEADS.add(newLead)
    fun addRecommendation(newRec: ItemLead) = RECS.add(newRec)

    data class ItemLead(val title: String, val details: String, val category: String, val imgId: Int): Serializable
}