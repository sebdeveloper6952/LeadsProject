package adapters

import DummyData.DummyData
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.projects.sebdeveloper6952.chapinleads.R
import kotlinx.android.synthetic.main.list_item_lead.view.*

class LeadItemRecyclerVAdapter(private val data: List<DummyData.ItemLead>):
        RecyclerView.Adapter<LeadItemRecyclerVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_lead, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        with(holder) {
            imgViewItem.setImageResource(item.imgId)
            txtViewTitle.text = item.title
            txtViewDetails.text = item.details
        }
    }

    override fun getItemCount() = data.size

    inner class ViewHolder(v: View): RecyclerView.ViewHolder(v) {
        val imgViewItem = v.imgView_item
        val txtViewTitle = v.txtView_title
        val txtViewDetails = v.txtView_details
    }
}