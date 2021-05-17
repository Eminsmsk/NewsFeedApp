package com.eminsimsek.newsfeedapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.eminsimsek.newsfeedapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    // apiKey = 2fe5d24c920c43bf850d5486f8ad649d
    lateinit var tempFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNav: BottomNavigationView = findViewById(R.id.bottomNav)
        tempFragment = NewsFragment()
        supportFragmentManager.beginTransaction().add(R.id.fragmentHolder, tempFragment)
            .commit()



        bottomNav.setOnNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.itemNews -> tempFragment = NewsFragment()
                R.id.itemFavorites -> tempFragment = FavoritesFragment()


            }

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentHolder, tempFragment).commit()



            true
        }

    }


}