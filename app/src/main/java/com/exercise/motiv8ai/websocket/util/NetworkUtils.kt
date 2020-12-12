package com.exercise.motiv8ai.websocket.util

import android.net.NetworkInfo
import android.telephony.TelephonyManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkUtils @Inject constructor(private val networkInfo: NetworkInfo?, private val telephonyManager: TelephonyManager?) {

    fun isNetworkAvailable() : Boolean = networkInfo?.isConnected ?: false

    fun getNetworkOperatorName(): String = telephonyManager?.networkOperatorName?: "unknown"
}