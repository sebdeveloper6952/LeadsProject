package com.projects.sebdeveloper6952.chapinleads.components

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.projects.sebdeveloper6952.chapinleads.R
import com.projects.sebdeveloper6952.chapinleads.dummy.DummyData
import kotlinx.android.synthetic.main.activity_new_lead.*
import org.jetbrains.anko.design.snackbar

class NewLeadActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_lead)
    }

    fun btnCreateLead(v: View) {
        val title = eTxt_title.text
        val details = eTxt_details.text
        val category = eTxt_category.text
        if(title == null || title.length == 0 || details == null || details.length == 0 ||
                category == null || category.length == 0) {
            snackbar(v, "Error en datos")
        } else {
            val newLead = DummyData.ItemLead(
                    title.toString(),
                    details.toString(),
                    category.toString(),
                    R.drawable.ic_image_black_24dp
            )
            // create intent result data to return to the previous activity
            val data = Intent().apply { putExtra(EXTRA_NEW_LEAD, newLead) }
            setResult(Activity.RESULT_OK, data)
            finish()
        }
    }

    companion object {
        val EXTRA_NEW_LEAD = "lead"
    }
}
