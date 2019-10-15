package com.example.floatbuttonapp

import com.google.gson.annotations.SerializedName

data class ResponseModel(
    @SerializedName("results")val results: List<Recipe>,
    @SerializedName("baseUri")val url: String,
    val offset: Int,
    val number:Int,
    val totalResults:Int
)