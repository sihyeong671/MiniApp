package com.example.miniapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.airbnb.lottie.LottieAnimationView
import com.example.miniapp.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity(){

    private lateinit var binding: ActivitySplashBinding

    private val permissions = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_CONTACTS)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 권한 물어보기
        checkPermissions()

//        requestWindowFeature(Window.FEATURE_NO_TITLE)


    }

    override fun onResume() {
        super.onResume()

        if(checkChange()){
            supportActionBar?.hide()

            binding = ActivitySplashBinding.inflate(layoutInflater)
            setContentView(binding.root)


            var lodingImage = findViewById(R.id.loading_image) as LottieAnimationView

            // 애니메이션 시작
            lodingImage.playAnimation()

            val handler: Handler = Handler()
            handler.postDelayed({
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            },1300)
        }

    }

    private fun checkChange(): Boolean {

        for(permisson in permissions){
            if(ContextCompat.checkSelfPermission(this, permisson) != PackageManager.PERMISSION_GRANTED){
                return false
            }
        }
        return true
    }

    private fun checkPermissions(){

        var rejectedPermissiontList = ArrayList<String>()

        for(permisson in permissions){
            if(ContextCompat.checkSelfPermission(this, permisson) != PackageManager.PERMISSION_GRANTED){
                rejectedPermissiontList.add(permisson)
            }
        }

        if(rejectedPermissiontList.isNotEmpty()){
            val array = arrayOfNulls<String>(rejectedPermissiontList.size)
            ActivityCompat.requestPermissions(this, rejectedPermissiontList.toArray(array), 100)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            100 -> {
                if(grantResults.isNotEmpty()){
                    for((i, permission) in permissions.withIndex()){
                        if(grantResults[i] != PackageManager.PERMISSION_GRANTED){
                            Log.d("TAG","권한에러")
                        }
                    }
                }
            }
        }
    }
}

