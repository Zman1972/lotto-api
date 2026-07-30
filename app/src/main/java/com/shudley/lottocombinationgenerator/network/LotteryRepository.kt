package com.shudley.lottocombinationgenerator.network

object LotteryRepository {

    fun getLatestResults(): String {
        return LotteryApi.getLatestResults()
    }

}