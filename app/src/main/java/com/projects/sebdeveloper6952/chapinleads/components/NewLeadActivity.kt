package com.projects.sebdeveloper6952.chapinleads.components

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.view.View
import com.projects.sebdeveloper6952.chapinleads.R
import com.projects.sebdeveloper6952.chapinleads.dummy.DummyData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_new_lead.*
import org.jetbrains.anko.design.snackbar
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class NewLeadActivity : AppCompatActivity() {

    private val RC_TAKE_PHOTO = 56
    private val RC_CHOOSE_PHOTO = 57
    private lateinit var mPhotoUri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_lead)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            RC_TAKE_PHOTO -> {
                // user took a photo, load image view with photo uri
                if(resultCode == Activity.RESULT_OK) {
                    Picasso.get().load(mPhotoUri).into(activity_new_lead_image_preview)
                }
            }
            RC_CHOOSE_PHOTO -> {
                if(resultCode == Activity.RESULT_OK) {  }
            }
        }
    }

    fun btnTakePhoto(v: View) {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        // this revision ensures there is a component on the phone that can take the photo
        if(intent.resolveActivity(packageManager) != null) {
            // TODO("learn about kotlin exception handling")
            val photoFile = createPhotoFile()
            val photoUri = FileProvider.getUriForFile(
                    this,
                    "com.example.android.fileprovider",
                    photoFile)
            mPhotoUri = photoUri
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
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
            val newLead = DummyData.Lead(
                    0,
                    title.toString(),
                    details.toString(),
                    category.toString(),
                    mPhotoUri.toString()
            )
            // create intent result data to return to the previous activity
            val data = Intent().apply { putExtra(EXTRA_NEW_LEAD, newLead) }
            setResult(Activity.RESULT_OK, data)
            finish()
        }
    }

    /**
     * Create a unique file, to save the photo the user took.
     */
    private fun createPhotoFile(): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
        val photoName = "photo_$timeStamp"
        val storageDir = File(Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                "ChapinLeads")
        return File.createTempFile(
                photoName,
                ".jpg",
                storageDir
        )
    }

    companion object {
        val EXTRA_NEW_LEAD = "lead"
    }
}
