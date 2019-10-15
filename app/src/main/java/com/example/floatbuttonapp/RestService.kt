package com.example.floatbuttonapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RestService {

    private var apiClient:ApiClient
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiClient = retrofit.create(ApiClient::class.java)
    }

    fun getApiClient()=apiClient
}