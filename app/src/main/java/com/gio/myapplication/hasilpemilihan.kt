package com.gio.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class hasilpemilihan : AppCompatActivity() {
    val database = FirebaseDatabase.getInstance("https://pilketosgg-default-rtdb.asia-southeast1.firebasedatabase.app/")
    private val myRef = database.getReference("calon")
    lateinit var calonList : ArrayList<modelcalon>
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_hasilpemilihan)
        FirebaseApp.initializeApp(this)
        recyclerView = findViewById(R.id.rcHasilPerhitungan)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        calonList = arrayListOf<modelcalon>()
        getData()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }
    private fun getData(){
        myRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                        calonList.clear()
                        for (dataSnapshot in snapshot.children){
                            val calon = dataSnapshot.getValue(modelcalon::class.java)
                            calonList.add(calon!!)
                        }
                        val adapter = AdapterPerhitungan(calonList)
                        recyclerView.adapter = adapter
                        adapter.notifyDataSetChanged()
                    }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@hasilpemilihan, error.message, Toast.LENGTH_SHORT).show()
            }

        })
    }
}