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

class PopulerAdapter(private val context: Context, private val populerList: ArrayList<Banner>, private val tokenAuth : String) :
    RecyclerView.Adapter<PopulerAdapter.MyViewHolderPopuler>() {

    class MyViewHolderPopuler(val viewPopuler: View): RecyclerView.ViewHolder(viewPopuler) {
        val gambar_populer = viewPopuler.findViewById<ImageView>(R.id.imageViewForPopuler)
        val cardViewPopuler = viewPopuler.findViewById<CardView>(R.id.banner_home_populer_img)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderPopuler {
        val layoutInflater = LayoutInflater.from(parent.context)
        val ItemViewPopuler = layoutInflater.inflate(R.layout.banner_home_populer, parent, false)
        return PopulerAdapter.MyViewHolderPopuler(ItemViewPopuler)
    }

    override fun getItemCount(): Int {
        return populerList.size
    }

    override fun onBindViewHolder(holder: MyViewHolderPopuler, position: Int) {
        Glide.with(context).load(populerList[position].banner).apply(RequestOptions().centerCrop().fitCenter()).into(holder.gambar_populer)
        holder.cardViewPopuler.setOnClickListener {
            //Toast.makeText(context, "" + populerList.get(position).title, Toast.LENGTH_SHORT).show()
            //mengirim intent slug ke halaman DetailMainActivity
            val intent = Intent(context, DetailMainActivity::class.java)
            intent.putExtra("slug", populerList[position].slug)

            //mengirim intent slug ke halaman ApiService
            val intentApi = Intent(context, ApiService::class.java)
            intentApi.putExtra("slug", populerList[position].slug)
            intent.putExtra("token_authorization", tokenAuth)

            context.startActivity(intent)
        }
    }

    fun setDataPopuler(dataPopuler: ArrayList<Banner>){
        populerList.clear()
        populerList.addAll(dataPopuler)
        notifyDataSetChanged()
    }

}