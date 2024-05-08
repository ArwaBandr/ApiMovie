package com.example.apimovie.data.remote

import com.example.apimovie.BuildConfig
import com.example.apimovie.model.GetMovieDetailsResponse
import com.example.apimovie.model.SearchResponse
import com.example.apimovie.model.UserAccount
import com.example.apimovie.model.UserTokenResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("3/movie/upcoming")
    suspend fun getUpcoming(
        @Query("api_key")
        apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("language")
        language: String = "en-US",
        @Query("page")
        page: Int = 1,
    ): Response<SearchResponse>


    @GET("3/movie/{movie_id}")
    suspend fun getMoveiDetails(
        @Path("movie_id")
        movieId: Int = 1,
        @Query("api_key")
        apikey: String = BuildConfig.TMDB_API_KEY,
        @Query("append_to_response")
        append_to_response: List<String>? = null,
        @Query("language")
        language: String = "en-US"
    ): Response<GetMovieDetailsResponse>
    @GET("3/search/multi")
    suspend fun getsearchMulti(
        @Query("api_key")
        apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("query")
        query:String="",
        @Query("include_adult")
        include_adult:Boolean=false,
        @Query("language")
        language:String="en-US",
        @Query("page")
        page: Int=1,
    ):Response<SearchResponse>

    @GET("3/authentication/token/new")
    suspend fun getUserToken(
        @Query("api_key")
        apiKey :String =BuildConfig.TMDB_API_KEY
    ):Response<UserTokenResponse>

    @GET("3/authentication/session/new")
    suspend fun getSessionId(
        @Query("api_key")
        apiKey :String =BuildConfig.TMDB_API_KEY,
        @Query("request_token")
        requestToken:String
    ):Response<UserTokenResponse>
  @GET("3/account")
  suspend fun getUserAccount(
      @Query("api_key")
      apiKey :String =BuildConfig.TMDB_API_KEY,
      @Query("session_id")
      sessionId:String
  ):Response<UserAccount>
}