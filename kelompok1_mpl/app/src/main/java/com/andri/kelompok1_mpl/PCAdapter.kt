package com.andri.kelompok1_mpl

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class PCAdapter(private val context: Context, private val pcList: ArrayList<Banner> , private val tokenAuth : String) :
    RecyclerView.Adapter<PCAdapter.MyViewHolderPC>() {
    class MyViewHolderPC(val viewPC: View): RecyclerView.ViewHolder(viewPC) {
        val gambar_pc = viewPC.findViewById<ImageView>(R.id.imageViewForPC)
        val cardViewPC = viewPC.findViewById<CardView>(R.id.banner_home_game_pc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderPC {
        val layoutInflater = LayoutInflater.from(parent.context)
        val ItemViewPC = layoutInflater.inflate(R.layout.banner_game_pc, parent, false)
        return PCAdapter.MyViewHolderPC(ItemViewPC)
    }

    override fun getItemCount(): Int {
        return pcList.size
    }

    override fun onBindViewHolder(holder: MyViewHolderPC, position: Int) {
        Glide.with(context).load(pcList[position].banner).apply(RequestOptions().centerCrop().fitCenter()).into(holder.gambar_pc)
        holder.cardViewPC.setOnClickListener{
            //Toast.makeText(context, "" + pcList.get(position).title, Toast.LENGTH_SHORT).show()
            //mengirim intent slug ke halaman DetailMainActivity
            val intent = Intent(context, DetailMainActivity::class.java)
            intent.putExtra("slug", pcList[position].slug)

            //mengirim intent slug ke halaman ApiService
            val intentApi = Intent(context, ApiService::class.java)
            intentApi.putExtra("slug", pcList[position].slug)
            intent.putExtra("token_authorization", tokenAuth)

            context.startActivity(intent)
        }
    }

    fun setDataPC(dataPC: ArrayList<Banner>){
        pcList.clear()
        pcList.addAll(dataPC)
        notifyDataSetChanged()
    }

}