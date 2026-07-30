package com.shudley.lottocombinationgenerator.utils

import com.shudley.lottocombinationgenerator.models.WinningResult
import org.json.JSONObject

object JsonParser {

    fun parse(json: String): WinningResult {

        val obj = JSONObject(json)

        val numbersArray = obj.getJSONArray("numbers")

        val numbers = mutableListOf<Int>()

        for (i in 0 until numbersArray.length()) {
            numbers.add(numbersArray.getInt(i))
        }

        return WinningResult(
            game = obj.getString("game"),
            drawDate = obj.getString("drawDate"),
            numbers = numbers,
            bonus = obj.getInt("bonus")
        )
    }
}