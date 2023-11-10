package com.example.app.model

import com.google.gson.annotations.SerializedName

data class IronModel (
    @SerializedName("result")
    val `result` :List<IronData>
)

data class IronData(
    @SerializedName("id")
    val `id` : Int,
    @SerializedName("title")
    val `title` : String,
    @SerializedName("image")
    val `image` : String
)