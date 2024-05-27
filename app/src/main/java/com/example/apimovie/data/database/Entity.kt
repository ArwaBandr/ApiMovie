package com.example.apimovie.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "cached_paging")
data class CashedEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id :Int,
    @ColumnInfo(name = "title")
    val title :String,
    @ColumnInfo(name = "vote_average")
    val voteAverage:Double?,
    @ColumnInfo(name = "poster_path")
    val posterPath :String,
    @ColumnInfo(name = "backdrop_path")
    val backdropPath :String,
    @ColumnInfo(name = "page")
    val page: Int
)