package com.example.nazarenopathfinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.nazarenopathfinder.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(){

    private lateinit var drawerLayout: DrawerLayout
    lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)

        drawerLayout = binding.drawerLayout
        val navController = findNavController(R.id.myNavHostFragment)

        val navigationView: NavigationView = binding.navView

        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.homeFragment -> {
                    navController.navigate(R.id.homeFragment)
                }
                R.id.viewPathFragment -> {
                    navController.navigate(R.id.viewPathFragment)
                }
                R.id.faqFragment -> {
                    navController.navigate(R.id.faqFragment)
                }
                R.id.aboutUsFragment -> {
                    navController.navigate(R.id.aboutUsFragment)
                }
            }
            true
        }

        bottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> navController.navigate(R.id.homeFragment)
                R.id.menu_add -> {
                    val repository = (application as PathFinderApplication).repository
                    val newPathSheet = NewPathSheet(null, repository)
                    newPathSheet.show(supportFragmentManager, "newPathTag")

                }
                R.id.menu_view_path -> navController.navigate(R.id.viewPathFragment)
            }
            true
        }

        NavigationUI.setupActionBarWithNavController(this,navController,drawerLayout)
        NavigationUI.setupWithNavController(binding.navView,navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return NavigationUI.navigateUp(navController,drawerLayout)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        when (item.itemId) {
            R.id.faqFragment -> {
                navController.navigate(R.id.faqFragment)
                return true
            }
            R.id.aboutUsFragment -> {
                navController.navigate(R.id.aboutUsFragment)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

}