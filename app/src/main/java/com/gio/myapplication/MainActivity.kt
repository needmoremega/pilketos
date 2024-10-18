package com.gio.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var btnext: Button;
    lateinit var btnlihathasil: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        btnext = findViewById(R.id.btn_next)
        btnlihathasil = findViewById(R.id.btn_lihathasil)
        btnext.setOnClickListener{
            val kecalon = Intent(this@MainActivity,
                pemilihan::class.java)
            startActivity(kecalon)
        }
        btnlihathasil.setOnClickListener{
            val kehasil = Intent(this@MainActivity,
                hasilpemilihan::class.java)
            startActivity(kehasil)
        }
//        val keCalon =
//            intent(this@MainActivity,
//                pemilihan::class.java)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}