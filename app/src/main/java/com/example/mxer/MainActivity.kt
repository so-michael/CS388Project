package com.example.mxer

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.mxer.fragments.BrowseFragment
import com.example.mxer.fragments.CommunityFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), Communicator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragmentManager: FragmentManager = supportFragmentManager
        findViewById<BottomNavigationView>(R.id.bottom_navigation).setOnItemSelectedListener {
            // Alias
                item ->
            var fragmentToShow: Fragment? = null
            when (item.itemId) {
                R.id.action_home -> {
                    fragmentToShow = BrowseFragment()
                }
//                R.id.action_compose -> {
//                    // Set it to the fragment we want to show
//                    fragmentToShow = ComposeFragment()
//                }
//                R.id.action_profile -> {
//                    fragmentToShow = ProfileFragment()
//                }
            }
            if (fragmentToShow != null) {
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragmentToShow).commit()
            }
            // Return true when we handled the interaction
            true
        }
        // Set default selection
        findViewById<BottomNavigationView>(R.id.bottom_navigation).selectedItemId = R.id.action_home
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_settings, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_settings){
            //val intent = Intent(this@MainActivity, SettingsActivity::class.java)
            //startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun passCommunity(community: Community) {
        //passes community id from browse to community view screen
        val bundle = Bundle()
        bundle.putString("CommunityId", community.getId())
        bundle.putString("Name", community.getName())
        val transaction = this.supportFragmentManager.beginTransaction()
        val fragment = CommunityFragment()
        fragment.arguments = bundle
        transaction.replace(R.id.flContainer, fragment).commit()
    }

    override fun passPost(post: Post) {
        TODO("Not yet implemented")
    }
    companion object {
        const val TAG = "MainActivity"
    }
}