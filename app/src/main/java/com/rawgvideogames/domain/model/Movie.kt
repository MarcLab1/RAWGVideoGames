package com.rawgvideogames.domain.model

import com.rawgvideogames.data.network.movies.Data

data class Movie(val data: Data?)
{
    var low : String
    var high : String
    init {
        low = data?.low ?: ""
        high = data?.high ?: ""
    }
}
