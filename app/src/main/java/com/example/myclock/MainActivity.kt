package com.example.myclock

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.myclock.databinding.ActivityMainBinding
import java.util.Locale

const val KEY_SECOND = "second"
const val KEY_RUNNING = "isRunning"
class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding
    private var isRunning = false
    private var second = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        savedInstanceState?.getInt(KEY_SECOND)?: 0
        savedInstanceState?.getBoolean(KEY_RUNNING)?: false
        runTimer()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(KEY_SECOND,second)
        outState.putBoolean(KEY_RUNNING,isRunning)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        second = savedInstanceState.getInt(KEY_SECOND)
        isRunning = savedInstanceState.getBoolean(KEY_RUNNING)
        super.onRestoreInstanceState(savedInstanceState)
    }
    private fun init() {
        binding.btnStart.setOnClickListener {
            isRunning = true
        }

        binding.btnPause.setOnClickListener {
            isRunning = false
        }

        binding.btnReset.setOnClickListener {
            isRunning = false
            second = 0
        }
    }

    private fun runTimer() {
       val handler = Handler()

        handler.post(object :Runnable{
            override fun run() {
                if (isRunning) {
                    second++
                }
                val sec = second % 60
                val minutes = (second % 3600) / 60
                val hours = second / 3600

                binding.txtVTimer.text =
                    String.format(Locale.getDefault(), "%2d:%02d:%02d", hours, minutes, sec)

                handler.postDelayed(this, 1000)
            }
        })
    }




}
