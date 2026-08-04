package com.shudley.lottocombinationgenerator.utils

object PrizeCalculator {

    fun getPrize(matches: Int, bonus: Boolean): String {
        return when {
            matches == 6 -> "🏆 Jackpot!"
            matches == 5 && bonus -> "🥇 Match 5 + Bonus"
            matches == 5 -> "🥈 Match 5"
            matches == 4 && bonus -> "🥉 Match 4 + Bonus"
            matches == 4 -> "🎉 Match 4"
            matches == 3 && bonus -> "🎊 Match 3 + Bonus"
            matches == 3 -> "✔ Match 3"
            matches == 2 && bonus -> "✔ Match 2 + Bonus"
            else -> "No prize"
        }
    }
}