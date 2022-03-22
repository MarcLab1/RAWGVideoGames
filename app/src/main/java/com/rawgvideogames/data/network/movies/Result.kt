package com.rawgvideogames.data.network.movies

import com.google.gson.annotations.SerializedName

data class Result(
    val id: Int?,
    val name: String?,
    val preview: String?,

    @SerializedName("data")
    val data: Data?
)