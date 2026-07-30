package com.shudley.lottocombinationgenerator.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shudley.lottocombinationgenerator.models.Game
import com.shudley.lottocombinationgenerator.utils.LotteryGenerator

@Composable
fun LottoApp(
    selectedGame: Game
) {

    var numbers by remember { mutableStateOf(emptyList<Int>()) }
    var powerBall by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = selectedGame.name,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                val result = LotteryGenerator.generate(selectedGame)
                numbers = result.numbers
                powerBall = result.powerBall ?: 0
            }
        ) {
            Text("Generate Numbers")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            numbers.forEach { number ->
                Ball(number)
            }
        }

        if (selectedGame.hasBonusBall) {

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "PowerBall",
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Ball(
                number = powerBall,
                color = Color.Red
            )
        }
    }
}

@Composable
fun Ball(
    number: Int,
    color: Color = Color(0xFFFFC107)
) {
    Box(
        modifier = Modifier
            .size(56.dp)
            .clip(CircleShape)
            .background(color),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = number.toString(),
            color = Color.Black,
            fontSize = 20.sp
        )
    }
}