package com.example.apimovie.domain.details

import com.example.apimovie.data.repositry.PopularMovieRepositry
import com.example.apimovie.model.GetMovieDetailsResponse
import com.example.apimovie.model.UIState
import dagger.Reusable
import javax.inject.Inject

@Reusable
class GetPopularMovieDetalisUseCase @Inject constructor(private val popularMovieRepositry: PopularMovieRepositry){

    suspend fun invoke(movieId:Int): UIState<GetMovieDetailsResponse> {
        return popularMovieRepositry.getMoveiDetails(movieId)
    }
}