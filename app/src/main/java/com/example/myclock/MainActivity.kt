package com.example.myclock

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.myclock.databinding.ActivityMainBinding
import java.util.Locale

class MainActivity : AppCompatActivity(), Runnable {
    private lateinit var binding: ActivityMainBinding
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable
    private var isRunning = false
    private var second = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handler = Handler()
        runnable = Runnable{run()}

        handler.post( runnable )

        binding.btnStart.setOnClickListener {
            isRunning = true
        }

        binding.btnPause.setOnClickListener {
            isRunning = false
        }

        binding.btnReset.setOnClickListener {
            isRunning = false
            second = 0
            binding.txtVTimer.text = getString(R.string.timer)
        }
    }

    override fun run() {
        runTimer()
        if (isRunning) {
            second++
        }
        handler.postDelayed(this, 1000)
    }

    private fun runTimer(){
        var sec = second % 60
        var minutes = (second % 3600) / 60
        var hours = second / 3600
        binding.txtVTimer.text =
            String.format(Locale.getDefault(), "%2d:%02d:%02d", hours, minutes, sec)
    }

}

    //TODO перенести код сюда
    private fun init() {

    }
