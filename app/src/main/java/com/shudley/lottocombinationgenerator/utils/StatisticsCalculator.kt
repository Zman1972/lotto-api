package com.shudley.lottocombinationgenerator.utils

import com.shudley.lottocombinationgenerator.models.Statistics
import com.shudley.lottocombinationgenerator.models.WinningResult

object StatisticsCalculator {

    fun calculate(results: List<WinningResult>): Statistics {

        val frequency = mutableMapOf<Int, Int>()

        results.forEach { draw ->
            draw.numbers.forEach { number ->
                frequency[number] = frequency.getOrDefault(number, 0) + 1
            }
        }

        val sorted = frequency.entries.sortedByDescending { it.value }

        return Statistics(
            hotNumbers = sorted.take(10).map { it.key },
            coldNumbers = sorted.takeLast(10).map { it.key },
            totalDraws = results.size
        )
    }
}