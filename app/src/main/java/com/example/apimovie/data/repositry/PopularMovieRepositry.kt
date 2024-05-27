package com.example.apimovie.data.repositry

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.apimovie.data.database.CashedDao
import com.example.apimovie.data.database.CashedEntity
import com.example.apimovie.data.paging.MoviePagingSource
import com.example.apimovie.data.remote.MovieApi
import com.example.apimovie.model.GetMovieDetailsResponse
import com.example.apimovie.model.Results
import com.example.apimovie.model.UIState
import com.example.apimovie.model.UserAccount
import com.example.apimovie.model.UserTokenResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PopularMovieRepositry @Inject constructor(
    val movieApi: MovieApi,
    val CashedDao:CashedDao
) {

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
    suspend fun getPopularMovie(): Flow<PagingData<Results>> {
        return Pager(
            config = PagingConfig(pageSize = 15, prefetchDistance = 2),
            pagingSourceFactory = {
                MoviePagingSource(movieApi, true, cahedDao = CashedDao)
            }
        ).flow
    }

    suspend fun getMoveiDetails(movieId: Int): UIState<GetMovieDetailsResponse> {
        try {
            val response = movieApi.getMoveiDetails(movieId)
            if (response.isSuccessful && response.body() != null) {
                return UIState.Success(response.body())
            } else {
                return UIState.Empty(message = response.message().toString())
            }
        } catch (e: Exception) {
            return UIState.Error(e.message.toString())
        }
    }

    suspend fun getSearchedMovie(SearchQuery: String): Flow<PagingData<Results>> {
        return Pager(
            config = PagingConfig(pageSize = 15, prefetchDistance = 2),
            pagingSourceFactory = { MoviePagingSource(movieApi, false, SearchQuery, CashedDao) }
        ).flow
    }

    suspend fun getUserToken(): UIState<UserTokenResponse> {
        try {
            val response = movieApi.getUserToken()
            if (response.isSuccessful && response.body() != null) {
                return UIState.Success(response.body())
            } else {
                return UIState.Empty(message = response.message().toString())
            }
        } catch (e: Exception) {
            return UIState.Error(e.message.toString())
        }
    }

    suspend fun getSessionId(requestToken: String): UIState<UserTokenResponse> {
        try {
            val response = movieApi.getSessionId(requestToken = requestToken)
            if (response.isSuccessful && response.body() != null) {
                return UIState.Success(response.body())
            } else {
                return UIState.Empty(message = response.message().toString())
            }
        } catch (e: Exception) {
            return UIState.Error(e.message.toString())
        }
    }

    suspend fun getUserAccount(sessionId: String): UIState<UserAccount> {
        try {
            val response = movieApi.getUserAccount(sessionId = sessionId)
            if (response.isSuccessful && response.body() != null) {
                return UIState.Success(response.body())
            } else {
                return UIState.Empty(message = response.message().toString())
            }
        } catch (e: Exception) {
            return UIState.Error(e.message.toString())
        }
    }


}