package DummyData

import com.projects.sebdeveloper6952.chapinleads.R
import java.io.Serializable

object DummyData {

    val LEADS: List<ItemLead>
    val RECS: List<ItemLead>

    init {
        LEADS = arrayListOf(
                ItemLead("Lead1", "Lead1 details...", R.drawable.ic_image_black_24dp),
                ItemLead("Lead2", "Lead2 details...", R.drawable.ic_image_black_24dp),
                ItemLead("Lead3", "Lead3 details...", R.drawable.ic_image_black_24dp),
                ItemLead("Lead4", "Lead4 details...", R.drawable.ic_image_black_24dp),
                ItemLead("Lead5", "Lead5 details...", R.drawable.ic_image_black_24dp),
                ItemLead("Lead6", "Lead6 details...", R.drawable.ic_image_black_24dp)
        )
        RECS = arrayListOf(
                ItemLead("Recommendation1", "Recommendation1 details...", R.drawable.ic_image_black_24dp),
                ItemLead("Recommendation2", "Recommendation2 details...", R.drawable.ic_image_black_24dp),
                ItemLead("Recommendation3", "Recommendation3 details...", R.drawable.ic_image_black_24dp),
                ItemLead("Recommendation4", "Recommendation4 details...", R.drawable.ic_image_black_24dp),
                ItemLead("Recommendation5", "Recommendation5 details...", R.drawable.ic_image_black_24dp),
                ItemLead("Recommendation6", "Recommendation6 details...", R.drawable.ic_image_black_24dp)
        )
    }

    data class ItemLead(val title: String, val details: String, val imgId: Int): Serializable
}