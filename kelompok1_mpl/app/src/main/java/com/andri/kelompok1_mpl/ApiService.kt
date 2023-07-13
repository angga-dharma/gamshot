package com.andri.kelompok1_mpl

import com.andri.kelompok1_mpl.data_class.DataResponse
import com.andri.kelompok1_mpl.data_class.DetailsGame
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ApiService {
    @GET("api/games")
    fun getPhotos(@Header("Authorization") token: String) : Call<ArrayList<Banner>>

    @GET("/api/user/profile")
    fun getProfile(@Header("Authorization") Authorization : String): Call<DataResponse>

    @GET("api/games/{slug}")
    fun getDetails(@Header("Authorization") Authorization : String,
                   @Path("slug") slug: String): Call<DetailsGame>
}