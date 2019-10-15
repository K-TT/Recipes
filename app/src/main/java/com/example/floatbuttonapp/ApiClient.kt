package com.example.floatbuttonapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiClient {

    @Headers(
        "X-RapidAPI-Host:webknox-recipes.p.rapidapi.com",
        "X-RapidAPI-Key:814a5a9ea6mshb14fa0c485ed861p149455jsn3d0f6df97456")
    @GET("recipes/search")
    fun getRecipes() : Call<ResponseModel>

}