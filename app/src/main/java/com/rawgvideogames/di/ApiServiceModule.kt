package com.rawgvideogames.di

import com.rawgvideogames.data.network.game.ApiService
import com.rawgvideogames.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {

    @Singleton
    @Provides
    fun provideApiService() : ApiService
    {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(
          Constants.BASE_URL).build().create(ApiService::class.java)
    }

}