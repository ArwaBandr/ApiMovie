package com.example.apimovie.domain.User

import com.example.apimovie.data.repositry.PopularMovieRepositry
import javax.inject.Inject

class GetUserTokenUseCase @Inject constructor(private val popularMovieRepositry: PopularMovieRepositry) {
    suspend  fun invoke()=popularMovieRepositry.getUserToken()
}