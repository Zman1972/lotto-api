package com.shudley.lottocombinationgenerator.screens

import com.shudley.lottocombinationgenerator.utils.TicketStorage
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
import androidx.compose.ui.unit.dp
import com.shudley.lottocombinationgenerator.models.Game

@Composable
fun OwnNumbersScreen(selectedGame: Game) {

    var selectedNumbers by remember { mutableStateOf(setOf<Int>()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Choose Your Numbers",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(6),
            modifier = Modifier
                .fillMaxWidth()
                .height(420.dp)
        ) {

            items((1..selectedGame.maxNumber).toList()) { number ->

                val selected = selectedNumbers.contains(number)

                Surface(
                    modifier = Modifier
                        .padding(6.dp)
                        .size(50.dp)
                        .clickable {
                            if (selected) {
                                selectedNumbers = selectedNumbers - number
                            } else if (selectedNumbers.size < selectedGame.numbersToPick) {
                                selectedNumbers = selectedNumbers + number
                            }
                        },
                    shape = CircleShape,
                    tonalElevation = if (selected) 8.dp else 2.dp
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = number.toString())
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Selected (${selectedNumbers.size}/${selectedGame.numbersToPick}): ${
                selectedNumbers.sorted().joinToString(", ")
            }"
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Button(
                onClick = {
                    selectedNumbers = emptySet()
                }
            ) {
                Text("Clear")
            }

            Button(
                enabled = selectedNumbers.size == selectedGame.numbersToPick,
                onClick = {
                    TicketStorage.save(
                        selectedNumbers.sorted().joinToString(" - ")
                    )
                }
            ) {
                Text("Save Ticket")
            }
        }
    }
}