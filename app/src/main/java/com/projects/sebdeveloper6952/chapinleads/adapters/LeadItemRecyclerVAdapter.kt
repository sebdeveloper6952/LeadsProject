package com.projects.sebdeveloper6952.chapinleads.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.projects.sebdeveloper6952.chapinleads.R
import kotlinx.android.synthetic.main.list_item_lead.view.*
import com.projects.sebdeveloper6952.chapinleads.dummy.DummyData.ItemLead

class LeadItemRecyclerVAdapter(private var mData: List<ItemLead>):
        RecyclerView.Adapter<LeadItemRecyclerVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_lead, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mData[position]
        with(holder) {
            imgViewItem.setImageResource(item.imgId)
            txtViewTitle.text = item.title
            txtViewDetails.text = item.details
        }
    }

    override fun getItemCount() = mData.size

    fun updateDataset(data: ArrayList<ItemLead>) {
        mData = data
        notifyDataSetChanged()
    }

    inner class ViewHolder(v: View): RecyclerView.ViewHolder(v) {
        val imgViewItem: ImageView = v.list_item_lead_image
        val txtViewTitle: TextView = v.list_item_lead_text_title
        val txtViewDetails: TextView = v.list_item_lead_text_details
    }
}