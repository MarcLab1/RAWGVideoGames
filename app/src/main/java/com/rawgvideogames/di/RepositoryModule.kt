package com.rawgvideogames.di

import com.rawgvideogames.data.network.game.ApiService
import com.rawgvideogames.data.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideGamesRepository(apiService: ApiService) : GamesRepository
    {
        return GamesRepository_Impl(apiService)
    }
    @Singleton
    @Provides
    fun provideGameDetailRepository(apiService: ApiService) : GameDetailRepository
    {
        return GameDetailRepository_Impl(apiService)
    }
    @Singleton
    @Provides
    fun provideScreenshotsRepository(apiService: ApiService) : ScreenshotsRepository
    {
        return ScreenshotsRepository_Impl(apiService)
    }
}