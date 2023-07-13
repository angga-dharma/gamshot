package com.andri.kelompok1_mpl

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RVAdapter(private val context: Context, private val dataList: ArrayList<Banner>, private val tokenAuth : String) :
    RecyclerView.Adapter<RVAdapter.MyViewHolder>() {

    class MyViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val gambar = view.findViewById<ImageView>(R.id.imageViewForBanner)
        val cardView = view.findViewById<CardView>(R.id.banner_home_img)

        //val gambar_populer = view.findViewById<ImageView>(R.id.imageViewForPopuler)
        //val cardViewPopuler = view.findViewById<CardView>(R.id.banner_home_populer_img)

        //val gambar_pc = view.findViewById<ImageView>(R.id.imageViewForPC)
        //val cardViewPc = view.findViewById<CardView>(R.id.banner_home_game_pc)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val ItemView = layoutInflater.inflate(R.layout.banner_home, parent, false)
        return MyViewHolder(ItemView)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(context).load(dataList[position].banner).into(holder.gambar)
        holder.cardView.setOnClickListener{
           // Toast.makeText(context, "" + dataList.get(position).title, Toast.LENGTH_SHORT).show()

            //mengirim intent slug ke halaman DetailMainActivity
            val intent = Intent(context, DetailMainActivity::class.java)
            intent.putExtra("slug", dataList[position].slug)

            //mengirim intent slug ke halaman ApiService
            val intentApi = Intent(context, ApiService::class.java)
            intentApi.putExtra("slug", dataList[position].slug)
            intent.putExtra("token_authorization", tokenAuth)

            context.startActivity(intent)
        }
    }

    fun setData(data: ArrayList<Banner>){
        dataList.clear()
        dataList.addAll(data)
        notifyDataSetChanged()
    }


}