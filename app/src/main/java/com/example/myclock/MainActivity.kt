package com.example.myclock

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.myclock.databinding.ActivityMainBinding
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable
    private var isPressed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handler = Handler()
        // выяснить работает ли без лупера(Looper.getMainLooper())
        var second = 0


        runnable = Runnable {
            second++
            handler.postDelayed(runnable, 1000)
            var sec = second % 60
            var minutes = (second % 3600) / 60
            var hours = second / 3600
            binding.txtVTimer.text =
                String.format(Locale.getDefault(), "%2d:%02d:%02d", hours, minutes, sec)
        }




        binding.btnStart.setOnClickListener {
           // isPressed = true
            handler.post(runnable)
        }

        binding.btnPause.setOnClickListener {
          //  isPressed = false
            handler.removeCallbacks(runnable)

        }

        binding.btnReset.setOnClickListener {
          //  isPressed = false
            handler.removeCallbacks(runnable)
            second = 0
            binding.txtVTimer.text = getString(R.string.timer)
        }



    }

}

    //TODO перенести код сюда
    private fun init() {

    }
