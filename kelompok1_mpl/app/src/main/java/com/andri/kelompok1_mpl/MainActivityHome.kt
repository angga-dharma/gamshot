package com.andri.kelompok1_mpl

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.andri.kelompok1_mpl.databinding.ActivityMainBinding
import com.andri.kelompok1_mpl.databinding.ActivityMainHomeBinding
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivityHome : AppCompatActivity() {

    private lateinit var binding : ActivityMainHomeBinding
    private lateinit var adapter : RVAdapter
    private lateinit var adapterPopuler : PopulerAdapter
    private lateinit var adapterPC : PCAdapter
    private lateinit var adapterPS : PSAdapter
    private lateinit var TokenAuth: String


    private val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8000")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(LoginInterface::class.java)

    private var backPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainHomeBinding.inflate(LayoutInflater.from(this))
        TokenAuth = intent.getStringExtra("token_authorization").toString()

        //kalau ini rusak balikin aja ke default
        setContentView(binding.root)

        adapter = RVAdapter(this@MainActivityHome, arrayListOf(), TokenAuth)
        binding.recycleBannerHome.adapter = adapter
        binding.recycleBannerHome.setHasFixedSize(true)

        adapterPopuler = PopulerAdapter(this@MainActivityHome, arrayListOf(), TokenAuth)
        binding.recycleBannerPopulerHome.adapter = adapterPopuler
        binding.recycleBannerPopulerHome.setHasFixedSize(true)

        adapterPC = PCAdapter(this@MainActivityHome, arrayListOf(), TokenAuth)
        binding.recycleBannerGamePc.adapter = adapterPC
        binding.recycleBannerGamePc.setHasFixedSize(true)

        adapterPS = PSAdapter(this@MainActivityHome, arrayListOf(), TokenAuth)
        binding.recycleBannerGamePs.adapter = adapterPS
        binding.recycleBannerGamePs.setHasFixedSize(true)



        binding.recycleBannerHome.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recycleBannerPopulerHome.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recycleBannerGamePc.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recycleBannerGamePs.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        remoteGetPhoto()

    }

    fun remoteGetPhoto(){
        ApiClient.apiService.getPhotos(TokenAuth).enqueue(object : Callback<ArrayList<Banner>>{
            override fun onResponse(
                call: Call<ArrayList<Banner>>,
                response: Response<ArrayList<Banner>>
            ) {
                if (response.isSuccessful){
                    val data = response.body()
                    setDatatoAdapter(data!!)
                    setDatatoAdapterPopuler(data!!)
                    setDatatoAdapterPC(data!!)
                    setDatatoAdapterPS(data!!)
                }
            }

            override fun onFailure(call: Call<ArrayList<Banner>>, t: Throwable) {
                //Log.d("onFailure: ", "" + t.stackTraceToString() )
                val errorMessage = "Gagal terhubung ke API: ${t.localizedMessage}"
                Toast.makeText(baseContext, errorMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun iniabout(view: View) {
        val intent = Intent(this, aboutus::class.java)
        startActivity(intent)
    }

    fun inihome(view: View) {
        val intent = Intent(this, MainActivityHome::class.java)
        startActivity(intent)
    }

    fun btnprofile(view: View) {
        val intent = Intent(this, profile::class.java)
        intent.putExtra("token_authorization", TokenAuth)
        startActivity(intent)
    }


    fun setDatatoAdapter(data: ArrayList<Banner>){
        adapter.setData(data)
    }

    fun setDatatoAdapterPopuler(dataPopuler: ArrayList<Banner>){
        adapterPopuler.setDataPopuler(dataPopuler)
    }

    fun setDatatoAdapterPC(dataPC: ArrayList<Banner>){
        adapterPC.setDataPC(dataPC)
    }

    fun setDatatoAdapterPS(dataPS : ArrayList<Banner>){
        adapterPS.setDataPS(dataPS)
    }

    override fun onBackPressed(){
        if(backPressedOnce){
            val onBack = super.onBackPressed()
            val data = intent.getStringExtra("token_authorization")
            val deviceName = Build.MODEL
            val call = service.logoutUser(data.toString(),deviceName)
            call.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    // Handle the response here
                    onBack
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    // Handle the error here
                    onBack
                }
            })
            return
        }

        backPressedOnce = true
        Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show()

        Handler(Looper.getMainLooper()).postDelayed({
            backPressedOnce = false
        }, 2000)
    }


}