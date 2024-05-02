package com.example.apimovie.domain.onboarding

import com.example.apimovie.data.datastore.MovieAppDataStore
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SaveIsFirstTimeinDataStoreUseCase @Inject constructor(private val dataStore: MovieAppDataStore){
    suspend operator fun invoke(ShowTipsPages: Boolean){
       return dataStore.SaveOnBoardingsatte(ShowPerfsPage =ShowTipsPages)
    }
}