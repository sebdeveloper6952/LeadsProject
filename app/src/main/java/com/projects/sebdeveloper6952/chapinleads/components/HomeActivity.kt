package com.projects.sebdeveloper6952.chapinleads.components

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.facebook.AccessToken
import com.facebook.AccessTokenTracker
import com.projects.sebdeveloper6952.chapinleads.R
import kotlinx.android.synthetic.main.activity_home.*
import org.jetbrains.anko.startActivity

class HomeActivity : AppCompatActivity() {

    // facebook access token tracker
    private lateinit var mAccessTokenTracker: AccessTokenTracker
    private var activeFragmentId = 0
    // app permissions
    private val PERMISSION_EXT_STORAGE = 1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        // set item selected listener for bottom_navigation
        activity_home_bottom_nav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        // add the initial RecommendationsFragment
        setFragment(RecommendationsFragment.newInstance())
        setupFbAccessTokenTracker()
    }

    override fun onResume() {
        super.onResume()
        checkAppPermissions()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode) {
            PERMISSION_EXT_STORAGE -> {
                // TODO("handle the case in which the user doesn't grant the permission")
            }
        }
    }

    fun setActionBarTitle(title: String) {
        supportActionBar?.title = title
    }

    private val mOnNavigationItemSelectedListener
            = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.nav_recommendations -> {
                if(activeFragmentId != 0) {
                    setFragment(RecommendationsFragment.newInstance())
                    activeFragmentId = 0
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_myLeads -> {
                if(activeFragmentId != 1) {
                    setFragment(MyLeadsFragment.newInstance())
                    activeFragmentId = 1
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_settings -> {
                if(activeFragmentId != 2) {
                    setFragment(SettingsFragment.newInstance())
                    activeFragmentId = 2
                }
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.activity_home_fragment_space, fragment)
                .commit()
    }

    private fun checkAppPermissions() {
        // read/write external storage permission not granted
        if(ContextCompat.checkSelfPermission(applicationContext,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    PERMISSION_EXT_STORAGE
            )
        }
    }

    /**
     * Uses facebook access token tracking to know when the user logs out and responds
     * finishing this activity and returning to LoginActivity
     */
    private fun setupFbAccessTokenTracker() {
        mAccessTokenTracker = object: AccessTokenTracker() {
            override fun onCurrentAccessTokenChanged(
                    oldAccessToken: AccessToken?, currentAccessToken: AccessToken?) {
                if(currentAccessToken == null) {
                    startActivity<LoginActivity>()
                    finish()
                }
            }
        }
    }
}
