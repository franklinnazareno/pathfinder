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
import com.example.nazarenopathfinder.databinding.ActivityLoginBinding
import com.example.nazarenopathfinder.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityLoginBinding>(this,R.layout.activity_login)
        val navController = findNavController(R.id.myNavHostFragment)
        setSupportActionBar(binding.toolbar)

        drawerLayout = binding.drawerLayout
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                // Show the back button for non-root destinations
                navController.graph.startDestinationId -> {
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                }
                else -> {
                    supportActionBar?.setDisplayHomeAsUpEnabled(true)
                }
            }
        }
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
