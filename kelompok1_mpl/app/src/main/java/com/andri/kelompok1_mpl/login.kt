package com.andri.kelompok1_mpl

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.andri.kelompok1_mpl.data_class.DataLogin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class login : AppCompatActivity() {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8000")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(LoginInterface::class.java)
    private var loginSuccess = false
    private final var TOKEN_AUTH = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val txvClick: TextView = findViewById(R.id.txvRegister)
        txvClick.setOnClickListener {
            val pindahIntent = Intent(this, register::class.java)
            startActivity(pindahIntent)
        }

        val btnLogin: Button = findViewById(R.id.btnlogin)
        btnLogin.setOnClickListener {
            val username = findViewById<EditText>(R.id.txtUsernama).text.toString()
            val password = findViewById<EditText>(R.id.txtPassword).text.toString()
            val deviceName = Build.MODEL

            val call = service.loginUser(username, password, deviceName)
            call.enqueue(object : Callback<DataLogin> {
                override fun onResponse(call: Call<DataLogin>, response: Response<DataLogin>) {
                    if (response.isSuccessful) {
                        // Login berhasil

                        val data = response.body()

                        Toast.makeText(this@login, "Login Successfully", Toast.LENGTH_SHORT).show()

                        loginSuccess = true

                        val intent = Intent(this@login, MainActivityHome::class.java)

                        TOKEN_AUTH = data?.token.toString()

                        intent.putExtra("token_authorization", TOKEN_AUTH)

                        startActivity(intent)

                        finish()

                    } else {
                        // Login gagal
                        loginSuccess = false
                        Toast.makeText(this@login, "Login gagal!", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<DataLogin>, t: Throwable) {
                    // Proses kegagalan request di sini
                    Toast.makeText(this@login, "Terjadi kesalahan: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}







