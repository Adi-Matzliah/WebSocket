package com.exercise.motiv8ai.websocket.data

import com.exercise.motiv8ai.websocket.R
import com.exercise.motiv8ai.websocket.data.dao.GroceryDao
import com.exercise.motiv8ai.websocket.data.entity.Grocery
import com.exercise.motiv8ai.websocket.util.ResourcesLoader
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.*
import okio.ByteString
import timber.log.Timber
import javax.inject.Inject
import kotlin.coroutines.coroutineContext


class RemoteRepository @Inject constructor(
    private val okHttpClient: OkHttpClient,
    private val gson: Gson,
    private val groceryDao: GroceryDao,
    private val resLoader: ResourcesLoader
) {

    private var wss: WebSocket? = null

    suspend fun fetchGroceries() =
        withContext(coroutineContext + Dispatchers.IO) {
            if (wss == null) {
                val request =
                    Request.Builder().url(resLoader.getString(R.string.web_socket_base_url)).build()
                wss = okHttpClient.newWebSocket(request, object : WebSocketListener() {
                    override fun onMessage(webSocket: WebSocket, text: String) {
                        Timber.d("Receiving : $text")
                        val grocery = gson.fromJson(text, Grocery::class.java)
                        groceryDao.insert(grocery)
                    }

                    override fun onFailure(
                        webSocket: WebSocket,
                        t: Throwable,
                        response: Response?
                    ) {
                        super.onFailure(webSocket, t, response)
                        Timber.e("Error : ${t.message}")
                    }
                })
            }
        }

    fun getGroceries() =
        groceryDao.getAll()

    fun stopWebSocket() {
        wss?.cancel()
        wss = null
    }

}


