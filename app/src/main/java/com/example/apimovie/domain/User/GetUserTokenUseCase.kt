package com.example.apimovie.domain.User

import androidx.datastore.dataStore
import com.example.apimovie.data.datastore.MovieAppDataStore
import com.example.apimovie.data.repositry.PopularMovieRepositry
import com.example.apimovie.model.UIState
import com.example.apimovie.model.UserTokenResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserTokenUseCase @Inject constructor(
    private val popularMovieRepositry: PopularMovieRepositry,
    private val dataStore: MovieAppDataStore
) {
    suspend fun invoke(): UIState<UserTokenResponse> {
        val token = popularMovieRepositry.getUserToken()

        if (token is UIState.Success) {
            dataStore.writeToken(token.data?.requestToken.toString())
        }
        return token
    }

    fun readToken(): Flow<String> {
        return dataStore.readToken()
    }
}