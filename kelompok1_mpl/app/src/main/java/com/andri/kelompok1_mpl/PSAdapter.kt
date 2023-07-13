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

class PSAdapter(private val context: Context, private val psList: ArrayList<Banner> , private val tokenAuth : String) :
    RecyclerView.Adapter<PSAdapter.MyViewHolderPS>()  {
    class MyViewHolderPS(val viewPS: View): RecyclerView.ViewHolder(viewPS) {
        val game_ps = viewPS.findViewById<ImageView>(R.id.imageViewForPS)
        val cardViewPopuler = viewPS.findViewById<CardView>(R.id.banner_home_ps_img)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderPS {
        val layoutInflater = LayoutInflater.from(parent.context)
        val ItemViewPS = layoutInflater.inflate(R.layout.banner_game_ps, parent, false)
        return PSAdapter.MyViewHolderPS(ItemViewPS)
    }

    override fun getItemCount(): Int {
        return psList.size
    }

    override fun onBindViewHolder(holder: MyViewHolderPS, position: Int) {
        Glide.with(context).load(psList[position].banner).apply(RequestOptions().centerCrop().fitCenter()).into(holder.game_ps)
        holder.cardViewPopuler.setOnClickListener {
            //Toast.makeText(context, "" + psList.get(position).title, Toast.LENGTH_SHORT).show()

            //mengirim intent slug ke halaman DetailMainActivity
            val intent = Intent(context, DetailMainActivity::class.java)
            intent.putExtra("slug", psList[position].slug)

            //mengirim intent slug ke halaman ApiService
            val intentApi = Intent(context, ApiService::class.java)
            intentApi.putExtra("slug", psList[position].slug)
            intent.putExtra("token_authorization", tokenAuth)


            context.startActivity(intent)
        }
    }

    fun setDataPS(dataPS: ArrayList<Banner>){
        psList.clear()
        psList.addAll(dataPS)
        notifyDataSetChanged()
    }


}