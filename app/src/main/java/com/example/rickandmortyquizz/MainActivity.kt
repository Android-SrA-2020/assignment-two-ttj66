package com.example.rickandmortyquizz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.rickandmortyquizz.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var drawLayout: DrawerLayout
      override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
      
        navController = this.findNavController(R.id.navNavHostFragment)
        drawLayout = binding.drawerLayout
        NavigationUI.setupActionBarWithNavController(this, navController,drawLayout)
        NavigationUI.setupWithNavController(binding.navView, navController)

    }
   override fun onSupportNavigateUp(): Boolean {
       val navController = this.findNavController(R.id.navNavHostFragment)
       if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
           return  super.onSupportNavigateUp()
       }
           super.onBackPressed()
       return    NavigationUI.navigateUp(navController,drawLayout)
   }


}


