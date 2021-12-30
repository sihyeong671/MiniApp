package com.example.miniapp

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity(){

    private val fl: FrameLayout by lazy {
        findViewById(R.id.fl_con)
    }

    private lateinit var homeFragment: HomeFragment
    private lateinit var contactsFragment: ContactsFragment


    override fun onCreate(savedInstanceState: Bundle?) { // 앱 최초 실행 시 수행
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)// xml 화면 뷰 연결

        val bottom_nav = findViewById<BottomNavigationView>(R.id.bottom_navigation)



        bottom_nav.setOnItemSelectedListener { item ->

            when(item.itemId){


                    R.id.nav_home -> {
                        bottom_nav.itemIconTintList = ContextCompat.getColorStateList(this, R.color.color_home)
                        changeFragment(HomeFragment())
                    }
                    R.id.nav_photo -> {
                        bottom_nav.itemIconTintList = ContextCompat.getColorStateList(this, R.color.color_photo)
                        val photoIntent = Intent(this, PhotoActivity::class.java)
                        startActivity(photoIntent)

                    }
                    R.id.nav_contacts -> {
                        bottom_nav.itemIconTintList = ContextCompat.getColorStateList(this, R.color.color_contacts)
                        changeFragment(ContactsFragment())
                    }
                    else -> {
                        bottom_nav.itemIconTintList = ContextCompat.getColorStateList(this, R.color.color_home)
                        changeFragment(HomeFragment())
                    }
                }
            true
        }
        bottom_nav.selectedItemId = R.id.nav_home
    }

    private fun changeFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.fl_con, fragment).commit()
    }


}