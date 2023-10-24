package com.example.islamicapp.Fragements

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


import com.example.islamy_project.api.model.RadioResponseItem
import com.example.islamy_project.databinding.ItemRadioBinding

class RadioAdapter() : RecyclerView.Adapter<RadioAdapter.ViewHolder>() {
    var channels = listOf<RadioResponseItem>()
    var onItemClickPlay: OnItemClickListener? = null
    var onItemClickStop: OnItemClickListener? = null
    lateinit var viewBinding: ItemRadioBinding

    class ViewHolder(var viewBinding: ItemRadioBinding) : RecyclerView.ViewHolder(viewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        viewBinding = ItemRadioBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewBinding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var items = channels?.get(position)
        holder.viewBinding.radioNameTv.text = items?.name

        onItemClickPlay.let {
            holder.viewBinding.icPlay.setOnClickListener {
                onItemClickPlay?.onItemClick(position, channels.get(position)!!)
            }
        }
        onItemClickStop.let { }
        holder.viewBinding.icStop.setOnClickListener {
            onItemClickStop?.onItemClick(position, channels.get(position)!!)
        }
    }

    override fun getItemCount(): Int = channels?.size ?: 0
    fun changeData(data: List<RadioResponseItem>) {
        this.channels = data
        notifyDataSetChanged()

    }
}

interface OnItemClickListener {
    fun onItemClick(position: Int, item: RadioResponseItem)

}