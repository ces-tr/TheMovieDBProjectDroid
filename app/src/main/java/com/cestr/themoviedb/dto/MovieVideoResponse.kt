package com.cestr.themoviedb.dto

import com.cestr.themoviedb.model.MovieVideoModel
import com.squareup.moshi.Json

data class MovieVideoResponseWrapper(

    @Json(name = "id")
    val movieId: Int = 0) {

    @Json(name ="results")
    val results: List<MovieVideoResponse>? = null

}


class MovieVideoResponse{

    @Json(name = "id")
    val id: String = ""

    @Json(name = "iso_639_1")
    val iso_639_1:String= ""

    @Json(name = "iso_3166_1")
    val iso_3166_1:String= ""

    @Json(name = "key")
    val key:String= ""

    @Json(name = "name")
    val name:String= ""

    @Json(name = "site")
    val site:String= ""

    @Json(name = "size")
    val size:Int= 0

    @Json(name = "type")
    val type:String= ""


}

fun MovieVideoResponse.toMovieVideoModel() = MovieVideoModel(

     key= key,
     name =name,
     site=site,
     type=type

)
/*
{
    "id": 12123,
    "results": [
    {
        "id": "535657310e0a262876002f68",
        "iso_639_1": "en",
        "iso_3166_1": "US",
        "key": "XmqvTVuOzgA",
        "name": "Chain Reaction: Trailer (1996)",
        "site": "YouTube",
        "size": 720,
        "type": "Trailer"
    },
    {
        "id": "594c2bd69251413108016b23",
        "iso_639_1": "en",
        "iso_3166_1": "US",
        "key": "ryKownrT6ag",
        "name": "Chain Reaction ≣ 1996 ≣ Trailer ᴴᴰ",
        "site": "YouTube",
        "size": 1080,
        "type": "Trailer"
    }
    ]
}*/