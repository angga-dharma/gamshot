package com.andri.kelompok1_mpl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class favorite : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var itemList: ArrayList<Int>
    private lateinit var itemAdapter: favorite_adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        init()
    }

    private fun init(){
        itemList = ArrayList()
        recyclerView = findViewById(R.id.list_favorite)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        addToList()
        itemAdapter = favorite_adapter(itemList)
        recyclerView.adapter = itemAdapter
    }

    private fun addToList(){
        itemList.add(R.drawable.apex)
        itemList.add(R.drawable.assassinscreed)
        itemList.add(R.drawable.cod)
        itemList.add(R.drawable.dota2)
        itemList.add(R.drawable.forhonor)
        itemList.add(R.drawable.fortnite)
        itemList.add(R.drawable.godofwar)
        itemList.add(R.drawable.overwatch2)
        itemList.add(R.drawable.rainbowsix)
        itemList.add(R.drawable.spiderman)
        itemList.add(R.drawable.thelastofus)
    }
}