package com.example.apimovie.domain.search_multi

import com.example.apimovie.data.repositry.PopularMovieRepositry
import dagger.Reusable
import javax.inject.Inject

@Reusable
class GetSearchedMultiUseCase @Inject constructor(private val popularMovieRepositry: PopularMovieRepositry){
    suspend fun invoke(SearchQuery:String) =popularMovieRepositry.getSearchedMovie(SearchQuery)

}