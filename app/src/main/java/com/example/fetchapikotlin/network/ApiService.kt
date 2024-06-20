package com.example.fetchapikotlin.network

import com.example.fetchapikotlin.model.Item
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("hiring.json")
    fun getItems(): Call<List<Item>>
}