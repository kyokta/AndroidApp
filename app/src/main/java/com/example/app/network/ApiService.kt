package com.example.app.network

import com.example.app.model.IronModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("data.php")
    fun getData() : Call<IronModel>
}