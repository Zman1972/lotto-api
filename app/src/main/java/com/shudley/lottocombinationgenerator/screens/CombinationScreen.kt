package com.shudley.lottocombinationgenerator.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shudley.lottocombinationgenerator.models.Game

@Composable
fun CombinationScreen(
    selectedGame: Game
) {

    var selectedNumbers by remember {
        mutableStateOf(listOf<Int>())
    }

    var combinations by remember {
        mutableStateOf(listOf<List<Int>>())
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "🧮 ${selectedGame.name}",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = "Select ${selectedGame.numbersToPick} numbers"
        )

        Spacer(modifier = Modifier.height(20.dp))

        for (row in 0 until ((selectedGame.maxNumber + 6) / 7)) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {

                for (col in 1..7) {

                    val number = row * 7 + col

                    if (number <= selectedGame.maxNumber) {

                        Button(
                            onClick = {

                                selectedNumbers =
                                    when {
                                        number in selectedNumbers ->
                                            selectedNumbers - number

                                        selectedNumbers.size < selectedGame.numbersToPick ->
                                            (selectedNumbers + number).sorted()

                                        else ->
                                            selectedNumbers
                                    }

                            },
                            modifier = Modifier.size(42.dp),
                            contentPadding = PaddingValues(0.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor =
                                    if (number in selectedNumbers)
                                        MaterialTheme.colorScheme.primary
                                    else
                                        MaterialTheme.colorScheme.surfaceVariant
                            )
                        ) {

                            Text(number.toString())
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(6.dp))
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text("Selected:")

        Text(
            text =
                if (selectedNumbers.isEmpty())
                    "None"
                else
                    selectedNumbers.joinToString(", ")
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {

                if (selectedNumbers.size == selectedGame.numbersToPick) {

                    combinations = List(5) {
                        selectedNumbers.shuffled()
                    }

                }

            },
            modifier = Modifier.fillMaxWidth()
        ) {

            Text("Generate Combinations")
        }

        Spacer(modifier = Modifier.height(20.dp))

        if (combinations.isNotEmpty()) {

            Text(
                text = "Generated Tickets",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(10.dp))

            combinations.forEachIndexed { index, combo ->

                Text(
                    text = "${index + 1}. ${combo.joinToString(" - ")}",
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }
    }
}