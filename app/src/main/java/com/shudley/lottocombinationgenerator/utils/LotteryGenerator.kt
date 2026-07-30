package com.shudley.lottocombinationgenerator.utils

import com.shudley.lottocombinationgenerator.models.Game
import kotlin.random.Random

data class QuickPickResult(
    val numbers: List<Int>,
    val powerBall: Int? = null
)

object LotteryGenerator {

    fun generate(game: Game): QuickPickResult {

        val numbers = mutableSetOf<Int>()

        while (numbers.size < game.numbersToPick) {
            numbers.add(Random.nextInt(1, game.maxNumber + 1))
        }

        val sortedNumbers = numbers.toList().sorted()

        val powerBall =
            if (game.hasBonusBall)
                Random.nextInt(1, 21)
            else
                null

        return QuickPickResult(
            numbers = sortedNumbers,
            powerBall = powerBall
        )
    }
}

