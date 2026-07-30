package com.shudley.lottocombinationgenerator.utils

import com.shudley.lottocombinationgenerator.models.TicketResult

object TicketChecker {

    fun checkTicket(
        ticket: List<Int>,
        winning: List<Int>,
        ticketBonus: Int?,
        winningBonus: Int?
    ): TicketResult {

        val matches = ticket.count { winning.contains(it) }

        val bonusMatched =
            ticketBonus != null &&
                    winningBonus != null &&
                    ticketBonus == winningBonus

        val message = when {
            matches == 6 -> "🎉 JACKPOT!"
            matches == 5 && bonusMatched -> "🥳 5 + Bonus"
            matches == 5 -> "🎯 5 Matches"
            matches == 4 -> "👏 4 Matches"
            matches == 3 -> "👍 3 Matches"
            matches == 2 && bonusMatched -> "🙂 2 + Bonus"
            else -> "❌ No Prize"
        }

        return TicketResult(
            matches,
            bonusMatched,
            message
        )
    }
}