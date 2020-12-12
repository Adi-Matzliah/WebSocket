package com.exercise.motiv8ai.websocket.util.di.module

import com.exercise.motiv8ai.websocket.network.TmdbApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import retrofit2.Retrofit

@InstallIn(ActivityRetainedComponent::class)
@Module
object MainModule {
    @Provides
    @ActivityRetainedScoped
    fun provideMainApi(retrofit: Retrofit): TmdbApi = retrofit.create(TmdbApi::class.java)
}