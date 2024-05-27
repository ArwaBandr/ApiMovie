package com.example.apimovie.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface CashedDao {
    @Query("SELECT * FROM cached_paging WHERE page =:page")
    suspend fun getMovisByPage(page:Int):List<CashedEntity>
    @Transaction
    @Query("SELECT * FROM cached_paging")
    suspend fun getCachedMovie(): List<CashedEntity>

    @Transaction
    @Query("DELETE FROM cached_paging WHERE page NOT IN (:page)")
    suspend fun deleteMoviesNotInPage(page: List<Int>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies :List<CashedEntity>)
}


