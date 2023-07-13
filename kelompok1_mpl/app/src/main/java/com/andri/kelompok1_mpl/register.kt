package com.andri.kelompok1_mpl

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofit = Retrofit.Builder()
    .baseUrl("http://10.0.2.2:8000")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val service = retrofit.create(RegisterInterface::class.java)

class register : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val txvClick: TextView = findViewById(R.id.txvlogin)
        txvClick.setOnClickListener(this)
        val btnRegister: Button = findViewById(R.id.btnregister)
        btnRegister.setOnClickListener {
            val username = findViewById<EditText>(R.id.txtUsernamereg).text.toString()
            val email = findViewById<EditText>(R.id.txtEmailreg).text.toString()
            val password = findViewById<EditText>(R.id.txtPasswordreg).text.toString()

            val call = service.registerUser(username, email, password)
            call.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    Toast.makeText(this@register, "Registrasi berhasil!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@register, login::class.java)
                    startActivity(intent)
                    finish()
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Toast.makeText(this@register, "Terjadi kesalahan: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }


    override fun onClick(v: View?) {
        if (v != null){
            when(v.id){
                R.id.txvlogin ->{
                    val pindahIntent = Intent(this, login::class.java)
                    startActivity(pindahIntent)
                }
            }
        }
    }
}