package com.example.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.stopwatch.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var seconds: Int = 0
    private var isRunning: Boolean = false
    private var wasRunning: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState != null){
            seconds = savedInstanceState.getInt("seconds")
            isRunning = savedInstanceState.getBoolean("isRunning")
            wasRunning = savedInstanceState.getBoolean("wasRunning")
        }
        runTimer()

        binding.buttonStart.setOnClickListener {
            isRunning = true
        }
        binding.buttonPause.setOnClickListener {
            isRunning = false
        }
        binding.buttonReset.setOnClickListener {
            isRunning = false
            seconds = 0
        }

    }

    override fun onResume() {
        super.onResume()
        isRunning = wasRunning
    }


    override fun onPause() {
        super.onPause()
        wasRunning = isRunning
        isRunning = false

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("seconds", seconds)
        outState.putBoolean("isRunning", isRunning)
        outState.putBoolean("wasRunning", wasRunning)
    }

    private fun runTimer() {

        val handler = Handler()
        val runnable = object: Runnable{
            override fun run() {
                val hours: Int = seconds / 3600
                val minutes: Int = (seconds % 3600) / 60
                val secs: Int = seconds % 60
                val time =  String.format("%d:%02d:%02d", hours, minutes, secs)
                binding.textViewTimer.text = time
                if (isRunning) {
                    seconds++
                }
                handler.postDelayed(this, 1000)
            }
        }
        handler.post(runnable)

    }
}
