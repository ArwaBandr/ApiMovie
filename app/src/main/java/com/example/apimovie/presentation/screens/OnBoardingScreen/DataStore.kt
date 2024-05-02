package com.example.apimovie.presentation.screens.OnBoardingScreen

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.apimovie.Constant.DATASTORE
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


class DataStore (context: Context) {
    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE)
    val onBoarding_key = booleanPreferencesKey("onBoarding_key")

    val onBoardingStatusFlow: kotlinx.coroutines.flow.Flow<Boolean> = context.dataStore.data.map {
        it[onBoarding_key] ?: true
    }

    suspend fun DoneOnBoarding(OnBoardingfinished: Boolean, context: Context) {

        context.dataStore.edit {
            it[onBoarding_key]=OnBoardingfinished

        }
    }
    fun DoneOnBoardingHelper(OnBoardingfinished: Boolean, context: Context){

        CoroutineScope(Dispatchers.IO).launch {
            DoneOnBoarding(OnBoardingfinished, context)
        }}



}


