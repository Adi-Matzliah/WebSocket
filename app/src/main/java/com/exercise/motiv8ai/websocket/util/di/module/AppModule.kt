package com.exercise.motiv8ai.websocket.util.di.module

import android.content.Context
import com.exercise.motiv8ai.websocket.R
import com.exercise.motiv8ai.websocket.util.ResourcesLoader
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString
import timber.log.Timber
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module(
    includes = [
        NetworkModule::class,
        StorageModule::class
    ]
)
object AppModule {

    @Provides
    @Singleton
    fun provideResourcesLoader(@ApplicationContext context: Context) = ResourcesLoader(context)

}