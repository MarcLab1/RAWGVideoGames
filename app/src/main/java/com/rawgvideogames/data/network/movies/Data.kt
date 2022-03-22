package com.rawgvideogames.data.network.movies

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("480")
    val low: String?,

    @SerializedName("max")
    val high: String?
)