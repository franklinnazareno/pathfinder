package com.example.nazarenopathfinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.nazarenopathfinder.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(){

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var navigationView: NavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)

        drawerLayout = binding.drawerLayout
        val navController = findNavController(R.id.myNavHostFragment)

        navigationView = findViewById(R.id.navView)

        navigationView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> {
                    navController.navigate(R.id.homeFragment)
                    drawerLayout.closeDrawer(GravityCompat.START)
                }
                R.id.viewPathFragment -> {
                    navController.navigate(R.id.viewPathFragment)
                    drawerLayout.closeDrawer(GravityCompat.START)
                }
                R.id.faqFragment -> {
                    navController.navigate(R.id.faqFragment)
                    drawerLayout.closeDrawer(GravityCompat.START)
                }
                R.id.aboutUsFragment -> {
                    navController.navigate(R.id.aboutUsFragment)
                    drawerLayout.closeDrawer(GravityCompat.START)
                }
                R.id.logoutItem -> {
                    val intent = Intent(this, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    this.finish()
                    startActivity(intent)
                }
            }
            NavigationUI.onNavDestinationSelected(item, navController)
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