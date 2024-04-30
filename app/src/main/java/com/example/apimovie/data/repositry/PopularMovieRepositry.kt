package com.example.apimovie.data.repositry

import com.example.apimovie.data.remote.MovieApi
import com.example.apimovie.model.SearchResponse
import com.example.apimovie.model.UIState
import javax.inject.Inject

class PopularMovieRepositry @Inject constructor(val movieApi:MovieApi){

    suspend fun getPopularMovie():UIState<SearchResponse>{
        try {
            val response =movieApi.getUpcoming()
            if (response.isSuccessful && response.body() != null){
                return UIState.Success(response.body())
            }else{
                return UIState.Empty(message = response.message().toString())
            }
        }catch (e:Exception){
            return UIState.Error(e.message.toString())
        }

    }

}