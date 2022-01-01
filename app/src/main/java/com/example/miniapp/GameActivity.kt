package com.example.miniapp
import android.app.PendingIntent.getActivity
import android.content.Intent
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.*
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

    private lateinit var vibe: Vibrator
    private lateinit var vibeManager: VibratorManager
    private lateinit var effect: VibrationEffect

    private var gameOver = false
    private var gameStart = false
    private var fishAppearTime:Double = 0.0
    private var handler = Handler(Looper.getMainLooper()) // 여기서 쓰레드를 가져와야한다

    // 버전에 따라 다르게 작동





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
        vibe = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){ // API31 이상만 사용가능
            vibeManager = getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
            vibeManager.defaultVibrator
        }else{
            @Suppress("DEPRECATION")
            getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        }
        effect = VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE)
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
                fishAppearTime = Math.random()*8000 + 2000
                gameStart = true
                handler.postDelayed(
                    Runnable {
                        vibe.vibrate(effect)
                    }, fishAppearTime.toLong()
                )
            }

        }
    }

    override fun dispatchTouchEvent(ev : MotionEvent?):Boolean {
        val dialog = FishingDialog()
        dialog.show(supportFragmentManager, "FishingDialog")

        return super.dispatchTouchEvent(ev)
    }
    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        return
    }

}