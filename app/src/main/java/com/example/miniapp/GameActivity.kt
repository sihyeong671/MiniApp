package com.example.miniapp
import android.app.PendingIntent.getActivity
import android.content.Intent
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.miniapp.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var binding: ActivityGameBinding
    private lateinit var sensorManager : SensorManager
    private var gameOver = false
    private var gameStart = false

    override fun onResume() {
        super.onResume()

        setUpSensorStuff()
    }

    override fun onCreate(savedInstanceState: Bundle?) { // 앱 최초 실행 시 수행
        super.onCreate(savedInstanceState)

        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.fishingGo.visibility = View.VISIBLE


    }

    private fun setUpSensorStuff(){
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION)?.also {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_GAME, SensorManager.SENSOR_DELAY_GAME)
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if(event?.sensor?.type == Sensor.TYPE_LINEAR_ACCELERATION){
            val xAxis = event.values[0]
            val yAxis = event.values[1]
            val zAxis = event.values[2]
            if ((xAxis >= 15.0 || yAxis >= 15.0 || zAxis >= 15.0) && !gameStart){
                binding.fishingGo.visibility = View.INVISIBLE
                val animation = AnimationUtils.loadAnimation(this, R.anim.anim_rotate)
                binding.rodView.startAnimation(animation)
                gameStart = true
            }

        }
    }

    override fun dispatchTouchEvent(ev : MotionEvent?):Boolean {
        val builder = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.dialog_getfish, null)

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
    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        return
    }

}