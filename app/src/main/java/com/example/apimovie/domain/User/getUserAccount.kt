package com.example.apimovie.domain.User

import com.example.apimovie.data.repositry.PopularMovieRepositry
import javax.inject.Inject

class GetUserAccountUseCase @Inject constructor(private val popularMovieRepositry: PopularMovieRepositry){
    suspend  fun invoke(sessionId:String) = popularMovieRepositry.getUserAccount(sessionId)
}