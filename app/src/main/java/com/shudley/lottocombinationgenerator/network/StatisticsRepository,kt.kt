package com.shudley.lottocombinationgenerator.network

import com.shudley.lottocombinationgenerator.models.WinningResult
import org.json.JSONArray

object StatisticsRepository {

    fun getWinningResults(): List<WinningResult> {

        val json = LotteryApi.getLatestResults()

        val results = mutableListOf<WinningResult>()

        try {
            val array = JSONArray(json)

            for (i in 0 until array.length()) {

                val obj = array.getJSONObject(i)

                val numbersArray = obj.getJSONArray("numbers")

                val numbers = mutableListOf<Int>()

                for (j in 0 until numbersArray.length()) {
                    numbers.add(numbersArray.getInt(j))
                }

                results.add(
                    WinningResult(
                        game = obj.getString("game"),
                        drawDate = obj.getString("drawDate"),
                        numbers = numbers,
                        bonus = obj.getInt("bonus")
                    )
                )
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

        return results
    }
}