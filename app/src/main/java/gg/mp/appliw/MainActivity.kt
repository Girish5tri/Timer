package gg.mp.appliw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var countDownTimer: CountDownTimer? = null
    private var timerDuration: Long = 10000
    private var pauseOffset: Long = 0
    var tvTimer: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val start: Button = findViewById(R.id.start)
        val pause: Button = findViewById(R.id.pause)
        val reset: Button = findViewById(R.id.reset)

        tvTimer = findViewById(R.id.tv)

        tvTimer?.text = "${(timerDuration/1000).toString()}"


        start.setOnClickListener {
            startTimer(pauseOffset)
            start.isClickable=false

        }

        pause.setOnClickListener {
            pauseTimer()
            start.isClickable=true
            start.backgroundTintList = this.getColorStateList(com.google.android.material.R.color.design_default_color_primary)
            start.text="Resume now"
        }

        reset.setOnClickListener {
            resetTimer()
            start.isClickable=true
            start.backgroundTintList = this.getColorStateList(com.google.android.material.R.color.design_default_color_primary)
            start.text="Start"
        }

    }

    private fun resetTimer() {
        countDownTimer?.cancel()
        tvTimer?.text = "${(timerDuration/1000).toString()}"
        countDownTimer = null
        pauseOffset = 0
    }

    private fun pauseTimer() {
        countDownTimer?.cancel()
    }

    private fun startTimer(pauseOffsetL: Long) {
        countDownTimer = object : CountDownTimer(timerDuration - pauseOffsetL, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                pauseOffset = timerDuration - millisUntilFinished
                tvTimer?.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                Toast.makeText(this@MainActivity, "Timer Completed", Toast.LENGTH_SHORT)
                    .show()
            }
        }.start()
    }
}
