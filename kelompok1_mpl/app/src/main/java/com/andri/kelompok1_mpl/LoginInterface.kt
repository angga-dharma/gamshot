package com.andri.kelompok1_mpl

import com.andri.kelompok1_mpl.data_class.DataLogin
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface LoginInterface {
    @FormUrlEncoded
    @POST("/api/user/login")
    fun loginUser(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("device_name") deviceName: String
    ): Call<DataLogin>

    @FormUrlEncoded
    @POST("/api/user/logout")
    fun logoutUser(
        @Header("authorization") authorization: String,
        @Field("device_name") deviceName: String
    ): Call<ResponseBody>
}