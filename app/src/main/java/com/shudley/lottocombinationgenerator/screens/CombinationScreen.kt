package com.shudley.lottocombinationgenerator.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.shudley.lottocombinationgenerator.models.Game

@Composable
fun CombinationScreen(game: Game) {

    val selectedNumbers = remember { mutableStateListOf<Int>() }

    var outputSize by remember {
        mutableIntStateOf(game.numbersToPick.coerceAtMost(6))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Combination Generator",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Selected: ${selectedNumbers.size}/20",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(5),
            modifier = Modifier.weight(1f)
        ) {

            items((1..game.maxNumber).toList()) { number ->

                val selected = selectedNumbers.contains(number)

                Box(
                    modifier = Modifier
                        .padding(6.dp)
                        .size(52.dp)
                        .clip(CircleShape)
                        .background(
                            if (selected)
                                MaterialTheme.colorScheme.primary
                            else
                                Color.LightGray
                        )
                        .clickable {

                            if (selected) {

                                selectedNumbers.remove(number)

                            } else if (selectedNumbers.size < 20) {

                                selectedNumbers.add(number)
                                selectedNumbers.sort()
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = number.toString(),
                        color = if (selected) Color.White else Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Output Size: $outputSize",
            style = MaterialTheme.typography.titleMedium
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            (1..6).forEach { size ->

                Button(
                    onClick = { outputSize = size }
                ) {
                    Text(size.toString())
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {

                val combinations =
                    com.shudley.lottocombinationgenerator.utils.CombinationGenerator.generate(
                        selectedNumbers,
                        outputSize
                    )

                println("Generated: ${combinations.size}")

                combinations.forEach {
                    println(it.joinToString(" "))
                }
            }
        ) {
            Text("Generate Combinations")
        }
    }
}