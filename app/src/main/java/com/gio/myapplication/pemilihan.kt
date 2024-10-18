package com.gio.myapplication

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.FirebaseApp
import com.google.firebase.database.FirebaseDatabase

class pemilihan : AppCompatActivity() {
    val database = FirebaseDatabase.getInstance("https://pilketosgg-default-rtdb.asia-southeast1.firebasedatabase.app/")
    val myRef = database.getReference("perhitungan")
    lateinit var cvcalon1 :CardView
    lateinit var cvcalon2 :CardView
    lateinit var cvcalon3 :CardView
    lateinit var cvcalon4 :CardView
    lateinit var btpilih  : Button
    var calondipilih      :String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pemilihan)
        try {
            FirebaseApp.initializeApp(this)
        } catch (e: Exception) {
            Log.e("Firebase", "Error initializing Firebase: ${e.message}", e)
        }
        cvcalon1 =findViewById(R.id.cvcalon1)
        cvcalon2 =findViewById(R.id.cvcalon2)
        cvcalon3 =findViewById(R.id.cvcalon3)
        cvcalon4 =findViewById(R.id.cvcalon4)
        btpilih  =findViewById(R.id.btpilih)
        btpilih.visibility = View.INVISIBLE
        warnaawal()

        cvcalon1.setOnClickListener{
            calondipilih="1"
            warnaawal()
            cvcalon1.setCardBackgroundColor(Color.parseColor("#000000"))
            btpilih.text="Pilih calon 1"
            btpilih.visibility =View.VISIBLE
        }
        cvcalon2.setOnClickListener{
            calondipilih="2"
            warnaawal()
            cvcalon2.setCardBackgroundColor(Color.parseColor("#000000"))
            btpilih.text="Pilih calon 2"
            btpilih.visibility =View.VISIBLE
        }

        cvcalon3.setOnClickListener{
            calondipilih="3"
            warnaawal()
            cvcalon3.setCardBackgroundColor(Color.parseColor("#000000"))
            btpilih.text="Pilih calon 3"
            btpilih.visibility =View.VISIBLE
        }

        cvcalon4.setOnClickListener{
            calondipilih="4"
            warnaawal()
            cvcalon4.setCardBackgroundColor(Color.parseColor("#000000"))
            btpilih.text="Pilih calon 4"
            btpilih.visibility =View.VISIBLE
        }
        btpilih.setOnClickListener{
            simpandata(calondipilih)
            val terimakasih = Intent(this,terimakasih::class.java)
            startActivity(terimakasih)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun warnaawal(){
        cvcalon1.setCardBackgroundColor(Color.parseColor("#1c5f9f"))
        cvcalon2.setCardBackgroundColor(Color.parseColor("#1c5f9f"))
        cvcalon3.setCardBackgroundColor(Color.parseColor("#1c5f9f"))
        cvcalon4.setCardBackgroundColor(Color.parseColor("#1c5f9f"))
    }
    fun simpandata(calon:String){
        val pilihanbaru = myRef.push()
        val postid = pilihanbaru.key.toString()
        val data = ModelPerhitungan(postid,calon)
        pilihanbaru.setValue(data)
            .addOnSuccessListener {
                Toast.makeText(this,"data berhasil disimpan",Toast.LENGTH_SHORT).show()
                finish()
            }
            .addOnFailureListener {
                Log.e("Firebase", "Error saving data: ${it.message}",it )
                Toast.makeText(this, "Error: Data not saved ", Toast.LENGTH_SHORT).show()
            }
    }
}