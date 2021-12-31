package com.example.miniapp
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.miniapp.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding

    override fun onCreate(savedInstanceState: Bundle?) { // 앱 최초 실행 시 수행
        super.onCreate(savedInstanceState)

        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.fishingGo.visibility = View.VISIBLE


    }

    override fun dispatchTouchEvent(ev : MotionEvent?):Boolean {
        Log.d("GameActivity", "The screen touched")
        binding.fishingGo.visibility = View.INVISIBLE
        val animation = AnimationUtils.loadAnimation(this, R.anim.anim_rotate)
        binding.rodView.startAnimation(animation)
        return super.dispatchTouchEvent(ev)
    }

}