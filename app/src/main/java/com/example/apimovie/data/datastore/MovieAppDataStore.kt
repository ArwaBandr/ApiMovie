package com.example.apimovie.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.apimovie.Constant.DATASTORE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE)
const val PREFERANSE_KEY = "onBoarding_key"
const val PREFERANSE_KEY2 = "Token_Key"

class MovieAppDataStore @Inject constructor(context: Context) {
    val onBoarding_key = booleanPreferencesKey(PREFERANSE_KEY)
    val Token_Key = stringPreferencesKey(PREFERANSE_KEY2)

    private val dataStore = context.dataStore

    suspend fun saveOnBoardingsatte(showPerfsPage: Boolean) {

        dataStore.edit {
            it[onBoarding_key] = showPerfsPage
        }
    }

    fun readOnboardingStatus(): Flow<Boolean> {
        return dataStore.data.map {
            it[onBoarding_key] ?: false
        }

    }


    suspend fun writeToken(requestToken :String) {
        dataStore.edit{
            it[Token_Key]=requestToken
        }

    }

     fun readToken(): Flow<String> {
        return dataStore.data.map {
            it[Token_Key] ?: ""
        }

    }


}


