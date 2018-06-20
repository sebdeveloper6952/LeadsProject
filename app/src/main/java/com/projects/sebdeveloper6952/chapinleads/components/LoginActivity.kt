package com.projects.sebdeveloper6952.chapinleads.components

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.projects.sebdeveloper6952.chapinleads.R
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class LoginActivity : AppCompatActivity() {

    private val FB_PERM_R_EMAIL = "email"
    private val FB_PERM_R_PUBLIC_PROFILE = "public_profile"
    private lateinit var fbCallbackManager: CallbackManager
    private val fbPermissions = arrayListOf(FB_PERM_R_EMAIL, FB_PERM_R_PUBLIC_PROFILE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupFbButton()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // forward onActivityResult to facebook callback manager
        fbCallbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    fun btnLogin(v: View) {
        // TODO("remove") test login progress bar
        progressBar_login.visibility = View.VISIBLE
        Handler().postDelayed({
            progressBar_login.visibility = View.GONE
            startActivity<HomeActivity>()
        }, 1_000)
    }

    fun btnRegisterNewAccount(v: View) {
        startActivity<RegisterNewUserActivity>()
    }

    /**
     * Registers a callback to the login button, to respond to login request.
     */
    private fun setupFbButton() {
        fbCallbackManager = CallbackManager.Factory.create()
        with(btn_facebook_login) {
            setReadPermissions(fbPermissions)
            registerCallback(fbCallbackManager,
                    object : FacebookCallback<LoginResult> {
                        override fun onSuccess(result: LoginResult) {
                            toast("FacebookButton: onSuccess()")
                        }

                        override fun onCancel() {
                            toast("FacebookButton: onCancel()")
                        }

                        override fun onError(error: FacebookException?) {
                            toast("FacebookButton: onError()")
                        }
                    })
        }
    }
}
