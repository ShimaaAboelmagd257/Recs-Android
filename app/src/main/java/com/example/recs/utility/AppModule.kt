package com.example.recs.utility

import android.content.Context
import com.example.recs.data.api.RecsClient
import com.example.recs.data.api.RemoteSource
import com.example.recs.data.firebase.FirebaseManager
import com.example.recs.data.repository.Repository
import com.example.recs.data.sharedprefrence.SharedPreference
import com.example.recs.data.sharedprefrence.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
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
    fun provideSharedPreferencesWrapper(context: Context): SharedPreference {
        return SharedPreferences(context)
    }

    @Provides
    @Singleton
    fun provideFirebaseManager(sharedPreference: SharedPreference): FirebaseManager {
        return FirebaseManager(sharedPreference)
    }
    @Provides
    fun provideRemoteSource():RemoteSource{
        return RecsClient()
    }

    @Provides
    fun provideRepository(remoteSource: RemoteSource , firebaseManager: FirebaseManager):Repository{
        return Repository(remoteSource,firebaseManager)
    }

}
