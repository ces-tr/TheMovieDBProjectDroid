package com.cestr.themoviedb.utils

import com.cestr.themoviedb.dto.MovieGenreResponse

class Utils {


    companion object{

        @JvmStatic
        var defaultLanguage: String = "defaultLanguage"

        @JvmStatic
        val movieIdParam : String ="movieId";

//        @JvmStatic
////        var genres: List<MovieGenreResponse>? = null


        @JvmStatic
        var genresCollection: HashMap<Int,String> = hashMapOf(28 to "Action",
                                                              12 to "Adventure",
                                                              16 to "Animation",
                                                              35 to "Comedy",
                                                              18 to "Drama",
                                                              27 to "Horror",
                                                              53 to "Thriller")
    }


}