package com.projects.sebdeveloper6952.chapinleads.components

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.projects.sebdeveloper6952.chapinleads.R
import kotlinx.android.synthetic.main.activity_register_new_user.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.startActivity

class RegisterNewUserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_new_user)
        progressBar_login.visibility = View.GONE
    }

    fun registerNewAccount(v: View) {
        // TODO("remove") testing progress bar
        progressBar_login.visibility = View.VISIBLE
        Handler().postDelayed({
            snackbar(v, getString(R.string.register_new_account_success))
            Handler().postDelayed({
                progressBar_login.visibility = View.GONE
                startActivity<HomeActivity>()
            }, 1_000)
        }, 1_000)
    }
}
