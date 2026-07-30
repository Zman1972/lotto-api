package com.shudley.lottocombinationgenerator.models

data class WinningNumber(

    val gameName: String,

    val numbers: List<Int>,

    val powerBall: Int? = null,

    val drawDate: String

)