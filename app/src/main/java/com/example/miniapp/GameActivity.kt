package com.example.miniapp
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.*
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.miniapp.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity(), SensorEventListener {

    public lateinit var binding: ActivityGameBinding
    private lateinit var sensorManager : SensorManager

    private lateinit var vibe: Vibrator
    private lateinit var vibeManager: VibratorManager
    private lateinit var effect: VibrationEffect

    private var startTime = System.currentTimeMillis()
    private var currentTime = System.currentTimeMillis()
    public var fishing = false
    public var gameOver = false
    public var gameStart = false
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

        val animation1 = AnimationUtils.loadAnimation(this, R.anim.anim_flow) //배 흔들리는 애니메이션
        binding.deckView.startAnimation(animation1)

        val animation2 = AnimationUtils.loadAnimation(this, R.anim.anim_rod_flow) //낚싯대 흔들거리는 애니메이션
        binding.rodView.startAnimation(animation2)


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




            if ((xAxis >= 15.0 || yAxis >= 15.0 || zAxis >= 15.0) && !gameStart && !gameOver){
                Log.d("TAG", "3")
                binding.fishingGo.visibility = View.INVISIBLE

                val animation = AnimationUtils.loadAnimation(this, R.anim.anim_rotate) //낚싯대 던지는 애니메이션
                binding.rodView.startAnimation(animation)

                fishAppearTime = Math.random()*8000 + 3000

                startTime = System.currentTimeMillis()
                gameStart = true
                handler.postDelayed( //fishappertime 뒤에 안에 있는 코드가 실행됨
                    Runnable {
                        vibe.vibrate(effect)
                        // 무언가 걸렸습니다 낚아주세요 텍스트 뷰
                        binding.fishCatch.visibility = View.VISIBLE
                        fishing = true
                    }, fishAppearTime.toLong()
                )
            }

            if(fishing && !gameOver){

                // 낚아올릴 경우
                currentTime = System.currentTimeMillis()
                if((xAxis >= 15.0 || yAxis >= 15.0 || zAxis >= 15.0 )&&(currentTime - startTime >= fishAppearTime + 500)){
                    Log.d("TAG", "1")
                    binding.fishCatch.visibility = View.INVISIBLE
                    handler.postDelayed(
                        Runnable {
                            Log.d("TAG", "2")
                            vibe.vibrate(effect)
                            val dialog = FishingDialog() //물고기 잡았습니다 창 띄우기
                            dialog.show(supportFragmentManager, "FishingDialog")
                        }, 1000
                    )

                    gameStart = false
                    gameOver = true
                    fishing = false
                }

                // 아무 반응 없을 경우
                if(currentTime - startTime >= fishAppearTime + 5000){
                    gameOver = true
                    gameStart = false
                    fishing = false
                    //물고기를 놓쳤습니다. 출력
                }

            }





        }
    }

     override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        return
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }

}