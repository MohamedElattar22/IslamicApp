package com.example.islamicapp.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.islamicapp.Data.Constants
import com.example.islamicapp.Data.SurahData
import com.example.islamicapp.R

class QuraanAdapter(private val surahList: List<SurahData>) : RecyclerView.Adapter<QuraanAdapter.ViewHolder>(){
   var onSurahItemClick : onSurahItemClick ?= null





    class ViewHolder (ItemView : View ) : RecyclerView.ViewHolder(ItemView){
        val surahName : TextView = ItemView.findViewById(R.id.tvSurahName)
        val surahNum : TextView = ItemView.findViewById(R.id.tvSurahNum)


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.sorah_layout , parent , false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return surahList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = surahList.get(position)
        holder.surahName.text = item.surahName
        holder.surahNum.text = item.surahNumber.toString()
        holder.itemView.setOnClickListener {
            onSurahItemClick?.onSuraClick(item)
        }
    }
}