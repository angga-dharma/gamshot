package com.andri.kelompok1_mpl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.andri.kelompok1_mpl.data_class.DetailsGame
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailMainActivity : AppCompatActivity() {

    private lateinit var txtnamadetailgame: TextView
    private lateinit var txtusia: TextView
    private lateinit var txtisideskripsi: TextView
    private lateinit var txtisiTrailer: TextView
    private lateinit var txtisipublisher: TextView
    private lateinit var gambardetail: ImageView

    private lateinit var TokenAuth: String


    private lateinit var dataSlugIntent: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_main)

        //intent
        dataSlugIntent = intent.getStringExtra("slug").toString()
        TokenAuth = intent.getStringExtra("token_authorization").toString()


        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8000") // Ganti dengan URL API Anda
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val ApiService = retrofit.create(ApiService::class.java)

        txtnamadetailgame = findViewById(R.id.txtnamadetailgame)
        txtusia = findViewById(R.id.txtusia)
        txtisideskripsi = findViewById(R.id.txtisideskripsi)
        txtisiTrailer = findViewById(R.id.txtisiTrailer)
        txtisipublisher = findViewById(R.id.txtisipublisher)
        gambardetail = findViewById(R.id.gambardetail)


        val call = ApiService.getDetails(TokenAuth, dataSlugIntent)
        call.enqueue(object : Callback<DetailsGame> {
            override fun onResponse(call: Call<DetailsGame>, response: Response<DetailsGame>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        // Lakukan sesuatu dengan data
                        Glide.with(this@DetailMainActivity).load(data.banner).into(gambardetail)
                        txtnamadetailgame.text = "${data.title}"
                        txtusia.text = "${data.age_rating}"
                        txtisideskripsi.text = "${data.description}"
                        txtisiTrailer.text = "${data.video_url}"
                        txtisipublisher.text = "${data.publisher}"


                    }
                } else {
                    println("Failed to fetch data from API: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<DetailsGame>, t: Throwable) {
                println("Error: ${t.message}")
            }
        })

    }
}