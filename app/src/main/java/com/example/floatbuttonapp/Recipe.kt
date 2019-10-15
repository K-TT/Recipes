package com.example.floatbuttonapp

import com.google.gson.annotations.SerializedName

data class Recipe(
    @SerializedName("id")val id: Int,
    @SerializedName("title")val name: String,
    @SerializedName("image")val image:String?,
    val description: String?

)