package com.example.vinilosapp.view

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.vinilosapp.R
import com.example.vinilosapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the navigation host fragment from this Activity
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        // Instantiate the navController using the NavHostFragment
        navController = navHostFragment.navController
        // Make sure actions in the ActionBar get propagated to the NavController
        Log.d("act", navController.toString())
        setSupportActionBar(findViewById(R.id.my_toolbar))
        setupActionBarWithNavController(navController)




        // FOR THE BOTTOM MENU - this can be deleted
        val navListener =
            BottomNavigationView.OnNavigationItemSelectedListener { item: MenuItem ->
                var selectedFragment: Fragment? = null
                val itemId = item.itemId
                if (itemId == R.id.albumFragment) {
                    selectedFragment = AlbumFragment()
                } else if (itemId == R.id.artistFragment) {
                    selectedFragment = ArtistFragment()
                } else if (itemId == R.id.collectorsFragment) {
                    selectedFragment = CollectorFragment()
                }
                // It will help to replace the
                // one fragment to other.
                if (selectedFragment != null) {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment, selectedFragment).commit()
                }
                true
            }
        val bottomNav : BottomNavigationView = findViewById(R.id.bottomNav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        bottomNav.setOnNavigationItemSelectedListener { item ->
            // In order to get the expected behavior, you have to call default Navigation method manually
            NavigationUI.onNavDestinationSelected(item, navController)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}