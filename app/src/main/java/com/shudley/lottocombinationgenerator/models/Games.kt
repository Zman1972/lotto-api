package com.shudley.lottocombinationgenerator.models

val games = listOf(

    Game(
        name = "SA Lotto",
        maxNumber = 52,
        numbersToPick = 6
    ),

    Game(
        name = "Lotto Plus 1",
        maxNumber = 52,
        numbersToPick = 6
    ),

    Game(
        name = "Lotto Plus 2",
        maxNumber = 52,
        numbersToPick = 6
    ),

    Game(
        name = "PowerBall",
        maxNumber = 50,
        numbersToPick = 5,
        hasBonusBall = true,
        maxBonusBall = 20
    ),

    Game(
        name = "PowerBall Plus",
        maxNumber = 50,
        numbersToPick = 5,
        hasBonusBall = true,
        maxBonusBall = 20
    ),

    Game(
        name = "Daily Lotto",
        maxNumber = 36,
        numbersToPick = 5
    ),

    Game(
        name = "Sportstake 13",
        maxNumber = 13,
        numbersToPick = 13
    )
)

