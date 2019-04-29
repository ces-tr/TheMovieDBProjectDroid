package com.cestr.themoviedb.model

import java.util.*

class MovieModel (

    val id : Int=0,
    val title: String = "",
    val imgUrl: String = "",
    val releaseDate: Date?,
    val vote_average: Double,
    val runtime: Int,
    val sinopsis:String
)