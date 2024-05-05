package com.example.apimovie.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.apimovie.BuildConfig
import com.example.apimovie.data.remote.MovieApi
import com.example.apimovie.model.Results
import okio.IOException

class MoviePagingSource(
    private val MovieApi:MovieApi,
    ):PagingSource<Int, Results>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Results> {
        return try {
            val currentPage=params.key ?:1
            val movies =MovieApi.getUpcoming(
                apiKey = BuildConfig.TMDB_API_KEY,
                page = currentPage
            )
            LoadResult.Page(
                data = movies.body()?.results.orEmpty(),
                prevKey = if (currentPage ==1) null else currentPage -1,
                nextKey = if (movies.body()?.results?.isEmpty() == true) null else movies.body()?.page!! +1
            )
        }catch (exception: IOException){
            return LoadResult.Error(exception)
        }catch (exception: Exception){
            return  LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Results>): Int? {
        return state.anchorPosition
    }

}