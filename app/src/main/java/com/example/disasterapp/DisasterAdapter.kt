package com.example.disasterapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DisasterAdapter(var dList: List<DisasterData> = emptyList()) :
    RecyclerView.Adapter<DisasterAdapter.DisasterViewHolder>() {
        inner class DisasterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val icon : ImageView = itemView.findViewById(R.id.imageView)
            val titleTv : TextView = itemView.findViewById(R.id.disasterTitle)
            val descTv : TextView = itemView.findViewById(R.id.disasterDesc)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisasterViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.disaster_item, parent, false)
            return DisasterViewHolder(view)
        }

        override fun getItemCount(): Int {
            return dList.size
        }

        override fun onBindViewHolder(holder: DisasterViewHolder, position: Int) {
            holder.icon.setImageResource(dList[position].icon)
            holder.titleTv.text = dList[position].title
            holder.descTv.text = dList[position].desc
        }
}