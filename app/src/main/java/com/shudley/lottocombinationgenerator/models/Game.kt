package com.shudley.lottocombinationgenerator.models

data class Game(
    val name: String,
    val maxNumber: Int,
    val numbersToPick: Int,
    val hasBonusBall: Boolean = false,
    val maxBonusBall: Int = 0
)

