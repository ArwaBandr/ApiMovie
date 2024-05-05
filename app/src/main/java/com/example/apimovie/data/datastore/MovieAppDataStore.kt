package com.example.apimovie.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.apimovie.Constant.DATASTORE
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE)
const val PREFERANSE_KEY= "onBoarding_key"


class MovieAppDataStore @Inject constructor(context: Context) {
    val onBoarding_key = booleanPreferencesKey(PREFERANSE_KEY)

    private val dataStore =context.dataStore

    suspend fun SaveOnBoardingsatte(ShowPerfsPage: Boolean) {

        dataStore.edit {
            it[onBoarding_key] = ShowPerfsPage
        }
    }
     fun readOnboardingStatus():Flow<Boolean> {
      return dataStore.data.map {
          it[onBoarding_key]?:false
      }

    }

}


