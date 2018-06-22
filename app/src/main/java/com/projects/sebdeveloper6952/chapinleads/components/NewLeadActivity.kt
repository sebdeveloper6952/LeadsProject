package com.projects.sebdeveloper6952.chapinleads.components

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import com.projects.sebdeveloper6952.chapinleads.R
import com.projects.sebdeveloper6952.chapinleads.dummy.DummyData
import kotlinx.android.synthetic.main.activity_new_lead.*
import org.jetbrains.anko.design.snackbar

class NewLeadActivity : AppCompatActivity() {

    private val RC_TAKE_PHOTO = 56
    private val RC_CHOOSE_PHOTO = 57

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_lead)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            RC_TAKE_PHOTO -> {
                // user took a photo, get image bitmap
                if(resultCode == Activity.RESULT_OK) {
                    val bitmap = data?.extras?.get("data") as Bitmap
                    activity_new_lead_image_preview.setImageBitmap(bitmap)
                }
            }
            RC_CHOOSE_PHOTO -> {
                if(resultCode == Activity.RESULT_OK) {

                }
            }
        }
    }

    fun btnTakePhoto(v: View) {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        // this revision ensures there is a component on the phone that can take the photo
        if(intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, RC_TAKE_PHOTO)
        }
    }

    fun btnChoosePhoto(v: View) {

    }

    fun btnCreateLead(v: View) {
        val title = activity_new_lead_edit_text_title.text
        val details = activity_new_lead_edit_text_details.text
        val category = activity_new_lead_edit_text_category.text
        if(title == null || title.length == 0 || details == null || details.length == 0 ||
                category == null || category.length == 0) {
            snackbar(v, getString(R.string.new_lead_error_in_data))
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
