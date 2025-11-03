package com.example.recs.utility

import android.content.Context
import com.example.recs.data.api.RecsClient
import com.example.recs.data.api.RemoteSource
import com.example.recs.data.repository.Repository
import com.example.recs.presentation.home.HomeUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ContextModule{



    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context):Context{
        return context.applicationContext
    }

    @Provides
    fun provideRemoteSource():RemoteSource{
        return RecsClient()
    }

    @Provides
    fun provideRepository(remoteSource: RemoteSource):Repository{
        return Repository(remoteSource)
    }

}
