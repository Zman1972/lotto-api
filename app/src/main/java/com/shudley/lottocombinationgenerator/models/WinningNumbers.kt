package com.shudley.lottocombinationgenerator.models

data class WinningNumbers(
    val game: String,
    val drawDate: String,
    val numbers: List<Int>,
    val bonus: Int? = null
)