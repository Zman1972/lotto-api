package com.shudley.lottocombinationgenerator.models

data class TicketResult(
    val matches: Int,
    val bonusMatched: Boolean,
    val message: String
)