package com.example.apimovie.domain.User

import com.example.apimovie.data.repositry.PopularMovieRepositry
import javax.inject.Inject

class GetSessionIdUseCase @Inject constructor(private val popularMovieRepositry: PopularMovieRepositry) {
    suspend  fun invoke(requestToken:String) =popularMovieRepositry.getSessionId(requestToken)
}