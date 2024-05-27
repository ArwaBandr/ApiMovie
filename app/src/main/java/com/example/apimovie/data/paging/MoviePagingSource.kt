package com.example.apimovie.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.apimovie.data.database.CashedDao
import com.example.apimovie.data.database.CashedEntity
import com.example.apimovie.data.remote.MovieApi
import com.example.apimovie.model.Results

class MoviePagingSource(
    private val MovieApi: MovieApi,
    var SelectedFunction :Boolean,
    var QuerySearch:String ?= null,
    private val cahedDao:CashedDao
) : PagingSource<Int, Results>() {



    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Results> {
        return try {
            val cashedMovies =cahedDao.getCachedMovie()
            val currentPage = params.key ?: 1

            try {
                val movies = if (SelectedFunction) {
                    MovieApi.getUpcoming(
                        page = currentPage
                    )
                } else {
                    MovieApi.getsearchMulti(
                        page = currentPage,
                        query = QuerySearch.toString()
                    )
                }
                val movieList = movies.body()?.results?.map {
                    CashedEntity(
                        id = it.id ?: -1,
                        title = it.title.orEmpty(),
                        voteAverage = it.voteAverage,
                        posterPath = it.posterPath.orEmpty(),
                        backdropPath = it.backdropPath.orEmpty(),
                        page = currentPage
                    )
                }
                cahedDao.insertMovies(movieList.orEmpty())
                val pagesToKeep = listOf(currentPage - 1, currentPage, currentPage + 1)
                cahedDao.deleteMoviesNotInPage(
                    pagesToKeep
                )

            LoadResult.Page(
                data = movies.body()?.results.orEmpty(),
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (movies.body()?.results?.isEmpty() == true) null else movies.body()?.page!! + 1
            )
        } catch (e:Exception){
            LoadResult.Page(
                data = cashedMovies.map {
                    Results(
                        false,
                        backdropPath =it.backdropPath,
                        id= it.id,
                        title = it.title,
                        posterPath = it.posterPath,
                        voteAverage = it.voteAverage
                    )
                }, prevKey = null,
                nextKey = null
            )

            }
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Results>): Int? {
        return state.anchorPosition
    }

}