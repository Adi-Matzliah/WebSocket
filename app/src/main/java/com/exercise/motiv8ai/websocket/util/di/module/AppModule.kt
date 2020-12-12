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

/*    @Provides
    @Singleton
    fun provideWebSocket(@ApplicationContext context: Context, okHttpClient: OkHttpClient, resLoader: ResourcesLoader): WebSocket {
        val request = Request.Builder().url(resLoader.getString(R.string.web_socket_base_url)).build()
        return okHttpClient.newWebSocket(request, object: WebSocketListener() {
            override fun onMessage(webSocket: WebSocket, text: String) {
                Timber.d("Receiving : $text")
            }

            override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
                Timber.d("Receiving bytes : ${bytes.hex()}")
            }

            override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                Timber.d("Closing : $code / $reason")
            }
        })
    }*/
}