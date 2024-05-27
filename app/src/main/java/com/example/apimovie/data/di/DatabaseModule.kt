package com.example.apimovie.data.di

import android.content.Context
import com.example.apimovie.data.database.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ):MovieDatabase{
        return MovieDatabase.getDatabase(context)
    }

    @Singleton
    @Provides
    fun provideMovieDao(database:MovieDatabase) = database.cashedDao()


//    @Singleton
//    @Provides
//    fun provideMovieDao(database: Moviedatabase): CashedDao { return database.cashedDao()}
//
    }