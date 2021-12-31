package com.example.miniapp

import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.miniapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var dataList : ArrayList<User> = arrayListOf()

    private val fl: FrameLayout by lazy {
        findViewById(R.id.fl_con)
    }

    private lateinit var homeFragment: HomeFragment
    private lateinit var contactsFragment: ContactsFragment
    private lateinit var photoFragment: PhotoFragment


    override fun onCreate(savedInstanceState: Bundle?) { // 앱 최초 실행 시 수행
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val bottom_nav = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        getContact()
        dataList = getStringArrayPref(this,"contact")

        bottom_nav.setOnItemSelectedListener { item ->
            changeFragment(
                when (item.itemId) {
                    R.id.nav_home -> {
                        bottom_nav.itemIconTintList =
                            ContextCompat.getColorStateList(this, R.color.color_home)
//                        bottom_nav.itemTextColor = ContextCompat.getColorStateList(this, R.color.color_home)
                        HomeFragment()
                    }
                    R.id.nav_photo -> {
                        bottom_nav.itemIconTintList =
                            ContextCompat.getColorStateList(this, R.color.color_photo)
//                        bottom_nav.itemTextColor = ContextCompat.getColorStateList(this, R.color.card_1)
                        PhotoFragment()
                    }
                    R.id.nav_contacts -> {
                        bottom_nav.itemIconTintList =
                            ContextCompat.getColorStateList(this, R.color.color_contacts)
//                        bottom_nav.itemTextColor = ContextCompat.getColorStateList(this, R.color.card_1)
                        ContactsFragment()
                    }
                    else -> {
                        bottom_nav.itemIconTintList =
                            ContextCompat.getColorStateList(this, R.color.color_home)
//                        bottom_nav.itemTextColor = ContextCompat.getColorStateList(this, R.color.card_1)
                        HomeFragment()
                    }
                }

            )
            true
        }
        bottom_nav.selectedItemId = R.id.nav_home
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fl_con, fragment).commit() //fl_con의 id를 가지는 Framelayout에 fragment 배치.
        intent.putExtra("DataList", dataList)
    }

    fun readContacts(view: View){
        getContact()
    }

    fun getContact(){
        val contactList: MutableList<User> = ArrayList()
        val contacts = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            null
        )
        while (contacts!!.moveToNext()){
            val name = contacts.getString(contacts.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
            val number = contacts.getString(contacts.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER))
            val obj = User(username = name, phNum = number)

            contactList.add(obj)
        }
        contacts.close()

        setStringArrayPref(this,"contact",contactList)

        Toast.makeText(this, "연락처 정보를 가져왔습니다.", Toast.LENGTH_SHORT).show()
    }




}