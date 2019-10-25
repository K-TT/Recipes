package com.example.floatbuttonapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity(){



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        switchFragment(0)
        bottomNavView.setOnNavigationItemSelectedListener { menuItem ->
            switchFragment(menuItem.itemId)
            true
        }

    }

    private fun switchFragment(menuId:Int?){
        val fragment: Fragment = when (menuId){
            R.id.favorite ->{
                FavoriteFragment()
            }
            R.id.collections ->{
                CollectionsFragment ()
            }
            R.id.addItem ->{
                AddingFragment()
            }
            R.id.search ->{
                SearchFragment()
            }
            else -> {
                SearchFragment()
            }
        }

        val fragmentTransaction:FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container,fragment)
        fragmentTransaction.commit()
    }

}
