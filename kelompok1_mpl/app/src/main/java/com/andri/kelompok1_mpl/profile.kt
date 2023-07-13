package com.andri.kelompok1_mpl

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.andri.kelompok1_mpl.data_class.DataResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class profile : AppCompatActivity(){
    private lateinit var txtisinama: TextView
    private lateinit var contohusername: TextView
    private lateinit var txtnamapengguna: TextView
    private lateinit var txttglprofile: TextView


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {



        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8000") // Ganti dengan URL API Anda
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val ApiService = retrofit.create(ApiService::class.java)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        txtisinama = findViewById(R.id.txtisinama)
        contohusername = findViewById(R.id.contohusername)
        txtnamapengguna = findViewById(R.id.txtnamapengguna)
        txttglprofile = findViewById(R.id.txttglprofile)

        val token = intent.getStringExtra("token_authorization").toString()
        val call = ApiService.getProfile(token) // Ganti dengan ID yang sesuai dengan kebutuhan Anda
        txtisinama.text = token
        call.enqueue(object : Callback<DataResponse> {
            override fun onResponse(call: Call<DataResponse>, response: Response<DataResponse>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        txtisinama.text = "${data.username}"
                        contohusername.text = "${data.email}"
                        txtnamapengguna.text = "${data.username}"
                        txttglprofile.text = "${data.joined_since}"
                    }
                } else {
                    println("Failed to fetch data from API: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                println("Error: ${t.message}")
            }
        })
    }

}