package com.cestr.themoviedb.dto

import com.cestr.themoviedb.model.MovieModel
import com.squareup.moshi.Json
import java.text.SimpleDateFormat
import java.util.*

data class MovieResponseWrapper(

    @Json(name = "page")
    val page: Int = 0,

    @Json(name ="results")
    val results: List<MovieResponse>? = null,

    @Json(name ="total_results")
    val total_results: Int = 0,


    @Json(name ="total_pages")
    var total_pages: Int = 0

)
{
}

class MovieResponse {

    @Json(name ="poster_path")
    val poster_path: String = ""

    @Json(name ="adult")
    val isAdult: Boolean = false

    @Json(name ="overview")
    val overview: String = ""

    @Json(name ="release_date")
    val release_date: String = ""

    @Json(name ="id")
    val id: Int = 0

    @Json(name ="original_title")
    val original_title: String = ""

    @Json(name ="original_language")
    val original_language: String = ""

    @Json(name ="title")
    val title: String = ""

    @Json(name ="backdrop_path")
    val backdrop_path: String = ""

    @Json(name ="popularity")
    val popularity: Double = 0.toDouble()

    @Json(name ="vote_count")
    val vote_count: Int = 0

    @Json(name ="video")
    val isVideo: Boolean = false

    @Json(name ="vote_average")
    val vote_average: Double = 0.toDouble()

    @Json(name ="runtime")
    val runtime : Int= 0

    fun getDateFromString( strDate:String) :Date{

        val formatter= SimpleDateFormat("yyyy-MM-dd",Locale.US)
        val result = formatter.parse(strDate)

        return result;
    }

}

fun MovieResponse.toMovieModel() = MovieModel(
    id = id,
    title = title,
    imgUrl = poster_path,
    releaseDate = getDateFromString(release_date),
    vote_average = vote_average,
    runtime = runtime,
    sinopsis =overview

)





/*
* {
    "adult": false,
    "backdrop_path": "/7RyHsO4yDXtBv1zUU3mTpHeQ0d5.jpg",
    "belongs_to_collection": {
        "id": 86311,
        "name": "Los vengadores - Colección",
        "poster_path": "/qJawKUQcIBha507UahUlX0keOT7.jpg",
        "backdrop_path": "/zuW6fOiusv4X9nnW3paHGfXcSll.jpg"
    },
    "budget": 500000000,
    "genres": [
        {
            "id": 12,
            "name": "Aventura"
        },
        {
            "id": 878,
            "name": "Ciencia ficción"
        },
        {
            "id": 28,
            "name": "Acción"
        }
    ],
    "homepage": "https://www.marvel.com/movies/avengers-endgame",
    "id": 299534,
    "imdb_id": "tt4154796",
    "original_language": "en",
    "original_title": "Avengers: Endgame",
    "overview": "Tras los sucesos de \"Vengadores: Infinity War\", los superhéroes que sobrevivieron a Thanos se reunen para poner en práctica un plan definitivo que podría acabar con el villano definitivamente. No saben si funcionará, pero es su única oportunidad de intentarlo. Cuarta entrega de la saga \"Vengadores\"",
    "popularity": 857.525,
    "poster_path": "/qwLbQSeFy6ht8skBtao7lAZjsDo.jpg",
    "production_companies": [
        {
            "id": 109755,
            "logo_path": "/hUCbTgfDPp1sIo8BOI0AaOMx1Si.png",
            "name": "Walt Disney Studios",
            "origin_country": "US"
        },
        {
            "id": 420,
            "logo_path": "/hUzeosd33nzE5MCNsZxCGEKTXaQ.png",
            "name": "Marvel Studios",
            "origin_country": "US"
        }
    ],
    "production_countries": [
        {
            "iso_3166_1": "US",
            "name": "United States of America"
        }
    ],
    "release_date": "2019-04-24",
    "revenue": 120000000,
    "runtime": 182,
    "spoken_languages": [
        {
            "iso_639_1": "en",
            "name": "English"
        }
    ],
    "status": "Released",
    "tagline": "",
    "title": "Vengadores: Endgame",
    "video": false,
    "vote_average": 8.8,
    "vote_count": 1021
}*/