
package com.example.quizapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.quizapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout:DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @Suppress("UNUSED_VARIABLE")
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        //part end
        drawerLayout=binding.drawerLayout
        val navController = this.findNavController(R.id.myNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
        NavigationUI.setupWithNavController(binding.navView,navController)
        NavigationUI.setupActionBarWithNavController(this,navController,drawerLayout)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return NavigationUI.navigateUp(navController,drawerLayout)
    }

    // TODO (01) Create the new TitleFragment
    // Select File->New->Fragment->Fragment (Blank)

    // TODO (02) Clean up the new TitleFragment
    // In our new TitleFragment

    // TODO (03) Use DataBindingUtil.inflate to inflate and return the titleFragment in onCreateView
    // In our new TitleFragment
    // R.layout.fragment_title
}
