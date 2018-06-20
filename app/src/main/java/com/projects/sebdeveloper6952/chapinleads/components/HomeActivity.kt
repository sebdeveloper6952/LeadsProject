package com.projects.sebdeveloper6952.chapinleads.components

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.projects.sebdeveloper6952.chapinleads.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {



    private val mOnNavigationItemSelectedListener
            = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.nav_recommendations -> {
                addFragment(RecommendationsFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_myLeads -> {
                addFragment(MyLeadsFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_settings -> {
                addFragment(SettingsFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        // set item selected listener for bottom_navigation
        bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        // add the initial RecommendationsFragment
        addFragment(RecommendationsFragment.newInstance())
    }

    fun setActionBarTitle(title: String) {
        supportActionBar?.title = title
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_space, fragment)
                .commit()
    }
}
