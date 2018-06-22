package com.projects.sebdeveloper6952.chapinleads.components

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.facebook.AccessToken
import com.facebook.AccessTokenTracker
import com.projects.sebdeveloper6952.chapinleads.R
import kotlinx.android.synthetic.main.activity_home.*
import org.jetbrains.anko.startActivity

class HomeActivity : AppCompatActivity() {

    // facebook access token tracker
    private lateinit var mAccessTokenTracker: AccessTokenTracker

    private val mOnNavigationItemSelectedListener
            = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.nav_recommendations -> {
                setFragment(RecommendationsFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_myLeads -> {
                setFragment(MyLeadsFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_settings -> {
                setFragment(SettingsFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        // set item selected listener for bottom_navigation
        activity_home_bottom_nav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        // add the initial RecommendationsFragment
        setFragment(RecommendationsFragment.newInstance())
        setupFbAccessTokenTracker()
    }

    fun setActionBarTitle(title: String) {
        supportActionBar?.title = title
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.activity_home_fragment_space, fragment)
                .commit()
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
