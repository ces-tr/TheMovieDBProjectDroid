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
    val poster_path: String? = ""

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
    val runtime : Int? = 0


    @Json(name ="genre_ids")
    val genre_ids: Array<Int>? = null

    fun getDateFromString( strDate:String) :Date? {
            try{
                    val formatter= SimpleDateFormat("yyyy-MM-dd",Locale.US)
                    val result = formatter.parse(strDate)

                    return result;}
            catch (e:Exception ) {


            }
        return null
    }

}

fun MovieResponse.toMovieModel() = MovieModel(
    id = id,
    title = title,
    imgUrl = poster_path,
    backDropImgUrl = backdrop_path,
    releaseDate = getDateFromString(release_date),
    vote_average = vote_average,
    runtime = runtime,
    sinopsis =overview,
    genre_ids = genre_ids

)
