package com.cestr.themoviedb.interfaces

import com.cestr.themoviedb.model.MovieModel

interface IOnMovieItemTouchListener{

        fun onMovieItemTouchedEvent( moviemodel: MovieModel);
 }
