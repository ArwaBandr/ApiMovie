package com.example.apimovie.data.repositry

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.example.apimovie.data.paging.MoviePagingSource
import com.example.apimovie.data.remote.MovieApi
import com.example.apimovie.model.Results
import com.example.apimovie.model.SearchResponse
import com.example.apimovie.model.UIState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PopularMovieRepositry @Inject constructor(val movieApi:MovieApi){

//    suspend fun getPopularMovie():UIState<SearchResponse>{
//        try {
//            val response =movieApi.getUpcoming()
//            if (response.isSuccessful && response.body() != null){
//                return UIState.Success(response.body())
//            }else{
//                return UIState.Empty(message = response.message().toString())
//            }
//        }catch (e:Exception){
//            return UIState.Error(e.message.toString())
//        }
//
//    }

    suspend fun getPopularMovie():Flow<PagingData<Results>>{
        return Pager(
            config = PagingConfig(pageSize = 15, prefetchDistance = 2),
            pagingSourceFactory = {
                MoviePagingSource(movieApi)
            }
        ).flow
    }
}