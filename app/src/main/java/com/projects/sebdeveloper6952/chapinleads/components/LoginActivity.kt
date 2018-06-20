package com.projects.sebdeveloper6952.chapinleads.components

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
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

    private lateinit var callbackManager: CallbackManager
    private val FB_PERM_R_EMAIL = "email"
    private val FB_PERM_R_PUBLIC_PROFILE = "public_profile"
    private val fbPermissions = arrayListOf(FB_PERM_R_EMAIL, FB_PERM_R_PUBLIC_PROFILE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // facebook testing
        callbackManager = CallbackManager.Factory.create()
        with(btn_facebook_login) {
            setReadPermissions(fbPermissions)
            registerCallback(callbackManager,
                    object: FacebookCallback<LoginResult> {
                        override fun onSuccess(result: LoginResult) {
                            toast("FacebookLogin: onSuccess()")
                        }
                        override fun onCancel() {
                            toast("FacebookLogin: onCancel()")
                        }
                        override fun onError(error: FacebookException?) {
                            toast("FacebookLogin: onError()")
                        }
                    })
        }

        LoginManager.getInstance().registerCallback(callbackManager,
                object: FacebookCallback<LoginResult> {
                    override fun onSuccess(result: LoginResult) {
                        toast("FacebookLogin: onSuccess()")
                    }
                    override fun onCancel() {
                        toast("FacebookLogin: onCancel()")
                    }
                    override fun onError(error: FacebookException?) {
                        toast("FacebookLogin: onError()")
                    }
                })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    fun btnLogin(v: View) {
        startActivity<HomeActivity>()
    }

    fun btnRegisterNewAccount(v: View) {
        startActivity<RegisterNewUserActivity>()
    }
}
