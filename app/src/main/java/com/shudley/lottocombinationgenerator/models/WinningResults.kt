package com.shudley.lottocombinationgenerator.models

data class WinningResult(
    val game: String,
    val drawDate: String,
    val numbers: List<Int>,
    val bonus: Int
)