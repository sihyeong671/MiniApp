package com.example.miniapp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.miniapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView







class TestData(
    private var data1: String? = null,
    private var data2: String? = null,
    private var data3: String? = null
){
    fun getData1(): String? {
        return data1
    }
    fun setData1(name: String) {
        this.data1 = data1
    }
    fun getData2(): String? {
        return data2
    }
    fun setData2(address: String) {
        this.data2 = data2
    }
    fun getData3(): String? {
        return data3
    }
    fun setData3(type: String) {
        this.data3 = data3
    }
}


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    private var images = ArrayList<String>()
    private val PICK_IMAGES_CODE = 0

    var dataList: ArrayList<TestData> = arrayListOf(
        TestData("첫 번째 데이터1", "두 번째 데이터1", "세 번째 데이터1"),
        TestData("첫 번째 데이터2", "두 번째 데이터2", "세 번째 데이터2"),
        TestData("첫 번째 데이터3", "두 번째 데이터3", "세 번째 데이터3"),
        TestData("첫 번째 데이터4", "두 번째 데이터4", "세 번째 데이터4"),
        TestData("첫 번째 데이터5", "두 번째 데이터5", "세 번째 데이터5"),
        TestData("첫 번째 데이터6", "두 번째 데이터6", "세 번째 데이터6"),
        TestData("첫 번째 데이터7", "두 번째 데이터7", "세 번째 데이터7"),
        TestData("첫 번째 데이터8", "두 번째 데이터8", "세 번째 데이터8"),
        TestData("첫 번째 데이터9", "두 번째 데이터9", "세 번째 데이터9"),
        TestData("첫 번째 데이터10", "두 번째 데이터10", "세 번째 데이터10"),
        TestData("첫 번째 데이터11", "두 번째 데이터11", "세 번째 데이터11"),
        TestData("첫 번째 데이터12", "두 번째 데이터12", "세 번째 데이터12"),
        TestData("첫 번째 데이터13", "두 번째 데이터13", "세 번째 데이터13"),
        TestData("첫 번째 데이터14", "두 번째 데이터14", "세 번째 데이터14"),
        TestData("첫 번째 데이터15", "두 번째 데이터15", "세 번째 데이터15")
    )


    override fun onCreate(savedInstanceState: Bundle?) { // 앱 최초 실행 시 수행
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottom_nav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val add_photo_btn = findViewById<ImageButton>(R.id.btn_add_photo)


        bottom_nav.setOnItemSelectedListener { item ->

            when(item.itemId){
                    R.id.nav_home -> {
                        bottom_nav.itemIconTintList = ContextCompat.getColorStateList(this, R.color.color_home)
                        add_photo_btn.visibility = View.INVISIBLE
                        changeFragment(HomeFragment())
                    }

                    R.id.nav_contacts -> {
                        bottom_nav.itemIconTintList = ContextCompat.getColorStateList(this, R.color.color_contacts)
                        add_photo_btn.visibility = View.INVISIBLE
                        changeFragment(ContactsFragment())
                    }

                    R.id.nav_photo -> {
                        bottom_nav.itemIconTintList = ContextCompat.getColorStateList(this, R.color.color_photo)
                        add_photo_btn.visibility = View.VISIBLE
                        changeFragment(PhotoFragment())
                    }

                    else -> {
                        bottom_nav.itemIconTintList = ContextCompat.getColorStateList(this, R.color.color_home)
                        add_photo_btn.visibility = View.INVISIBLE

                        changeFragment(HomeFragment())

                    }
                }
            true
        }
        bottom_nav.selectedItemId = R.id.nav_home


        // pick images clicking this button
        add_photo_btn.setOnClickListener {
            pickImagesIntent()
        }
    }


    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fl_con, fragment).commit() //fl_con의 id를 가지는 Framelayout에 fragment 배치.
        intent.putExtra("DataList", dataList)
    }

    private fun pickImagesIntent(){
        val intent = Intent()
        intent.type = "image/*"
        intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(Intent.createChooser(intent, "Select Image(s)"), PICK_IMAGES_CODE)
//        startActivityForResult(intent, PICK_IMAGES_CODE)


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGES_CODE){
            if(resultCode == Activity.RESULT_OK){
                images.clear()

                val fragmentManager: FragmentManager = supportFragmentManager
                val fragmentTransaction : FragmentTransaction = fragmentManager.beginTransaction()
                val photoFragment = PhotoFragment()
                val bundle = Bundle()

                if(data!!.clipData != null){
                    //pick multiple images
                    val count = data.clipData!!.itemCount
                    for(i in 0 until count){
                        val imagesUri = data.clipData!!.getItemAt(i).uri
                        images!!.add(imagesUri.toString())
                    }
                    bundle.putStringArrayList("img", images)

                }
                else{
                    data?.data?.let { uri ->
                        val imageUri : Uri? = data?.data
                        if(imageUri != null){
                            images!!.add(imageUri.toString())
                        }
                    }
                    bundle.putStringArrayList("img", images)

                }

                photoFragment.arguments = bundle
                fragmentTransaction.add(R.id.fl_con, photoFragment).commit()
            }
        }
    }


}