package com.andri.kelompok1_mpl

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class favorite_adapter(private val itemList:List<Int>) :
    RecyclerView.Adapter<favorite_adapter.ItemViewHolder>() {
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val ivFavorite : ImageView = itemView.findViewById(R.id.imageViewFavorite)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.each_favorite,parent,false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.ivFavorite.setImageResource(itemList[position])
    }
}