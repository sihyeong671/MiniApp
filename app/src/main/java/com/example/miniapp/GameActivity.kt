package com.example.miniapp
import android.app.PendingIntent.getActivity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
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
        val builder = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.dialog_getfish, null)

        Log.d("GameActivity", "The screen touched")
        binding.fishingGo.visibility = View.INVISIBLE
        val animation = AnimationUtils.loadAnimation(this, R.anim.anim_rotate)
        binding.rodView.startAnimation(animation)


        builder.setView(dialogView)
            .setPositiveButton("나가기") { dialogInterface, i ->

            }
            .setNegativeButton("재도전") { dialogInterface, i ->
                binding.fishingGo.visibility = View.VISIBLE
                /* 취소일 때 아무 액션이 없으므로 빈칸 */
            }
            .show()

        return super.dispatchTouchEvent(ev)
    }

}