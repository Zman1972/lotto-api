package com.shudley.lottocombinationgenerator.network

import okhttp3.OkHttpClient
import okhttp3.Request

object LotteryApi {

    private val client = OkHttpClient()

    private const val URL =
        "https://raw.githubusercontent.com/Zman1972/lotto-api/main/results.json"

    fun getLatestResults(): String {

        val request = Request.Builder()
            .url(URL)
            .build()

        return try {

            val response = client.newCall(request).execute()

            if (response.isSuccessful) {
                response.body?.string() ?: "Empty response"
            } else {
                "HTTP ${response.code}: ${response.message}"
            }

        } catch (e: Exception) {
            "Error: ${e.javaClass.simpleName}\n${e.message}"
        }
    }
}