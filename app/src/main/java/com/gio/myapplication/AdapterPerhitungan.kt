package com.gio.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.core.Context

class AdapterPerhitungan (private  val listHasil: ArrayList<modelcalon>):
        RecyclerView.Adapter<AdapterPerhitungan.MyViewHolder>() {
            lateinit var appContext:android.content.Context
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val  tvNomorCalon: TextView = itemView.findViewById(R.id.idcalon)
        val tvNamaCalon: TextView = itemView.findViewById(R.id.nama)
        val tvHasil: TextView = itemView.findViewById(R.id.jumlahsuara)
        val ivfoto: ImageView = itemView.findViewById(R.id.foto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_calon, parent, false)
        appContext = parent.context
        return MyViewHolder(itemView)
    }


    override fun getItemCount():listHasil

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = listHasil[position]
        holder.tvNomorCalon.text = item.idcalon
        holder.tvNamaCalon.text = item.nama
        holder.tvHasil.text = "0"
//        holder.ivfoto.setImageURI()
    }
}