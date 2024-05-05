package com.example.apimovie.domain.popular

import androidx.annotation.RestrictTo
import androidx.paging.PagingData
import com.example.apimovie.data.repositry.PopularMovieRepositry
import com.example.apimovie.model.Results
import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@Reusable
class GetPopularMoviewUseCase @Inject constructor(private val popularMovieRepositry: PopularMovieRepositry){
   // suspend fun invoke() =popularMovieRepositry.getPopularMovie()
   suspend fun invoke():Flow<PagingData<Results>>{
       return popularMovieRepositry.getPopularMovie()
   }
}