package com.example.islamicapp.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.islamicapp.R

class suraContentAdapter (var verseList: List<String>?=null) :
    Adapter<suraContentAdapter.suraContentViewHolder>() {
    class suraContentViewHolder(val view : View) : RecyclerView.ViewHolder(view){
        val versTextView : TextView = view.findViewById(R.id.suraContentTv)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): suraContentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.sura_content_layout , parent,false )
        return suraContentViewHolder(view)
    }
    fun updateData(versesList: List<String>?){
            this.verseList = versesList
        notifyDataSetChanged()

    }

    override fun getItemCount(): Int {
        return verseList?.size ?: 0
    }

    override fun onBindViewHolder(holder: suraContentViewHolder, position: Int) {
        val item = verseList?.get(position)
        holder.versTextView.text = item
    }
}