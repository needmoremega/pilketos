package com.gio.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class terimakasih : AppCompatActivity() {
    private lateinit var textViewTimer: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        // ...
        textViewTimer = findViewById(R.id.timer)

        object : CountDownTimer(30000, 1000) { // Timer selama 30 detik, update setiap 1 detik
            override fun onTick(millisUntilFinished: Long) {
                textViewTimer.text = "Waktu tersisa: ${millisUntilFinished / 1000} detik"
            }

            override fun onFinish() {
                val intent = Intent(this@TimerActivity, MainActivity::class.java)
                startActivity(intent)
                finish() // Akhiri activity ini
            }
        }.start()
    }
}