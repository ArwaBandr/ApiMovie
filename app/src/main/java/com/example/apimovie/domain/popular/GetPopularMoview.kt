package com.example.apimovie.domain.popular

import androidx.annotation.RestrictTo
import com.example.apimovie.data.repositry.PopularMovieRepositry
import dagger.Reusable
import javax.inject.Inject

@Reusable
class GetPopularMoviewUseCase @Inject constructor(private val popularMovieRepositry: PopularMovieRepositry){
    suspend fun invoke() =popularMovieRepositry.getPopularMovie()

}