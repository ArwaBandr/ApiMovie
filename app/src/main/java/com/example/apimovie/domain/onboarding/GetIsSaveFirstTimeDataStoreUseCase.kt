package com.example.apimovie.domain.onboarding

import com.example.apimovie.data.datastore.MovieAppDataStore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetIsSaveFirstTimeDataStoreUseCase @Inject constructor(private val datastore: MovieAppDataStore) {

    operator fun invoke(): Flow<Boolean> {
        return datastore.readOnboardingStatus()
    }
}