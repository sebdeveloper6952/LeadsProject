package com.projects.sebdeveloper6952.chapinleads.components

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.projects.sebdeveloper6952.chapinleads.R
import org.jetbrains.anko.design.snackbar

class RegisterNewUserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_new_user)
    }

    fun registerNewAccount(v: View) {
        snackbar(v, getString(R.string.register_new_account_success))
    }
}
