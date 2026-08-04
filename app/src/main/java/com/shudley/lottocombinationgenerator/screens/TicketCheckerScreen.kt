package com.shudley.lottocombinationgenerator.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shudley.lottocombinationgenerator.utils.PrizeCalculator
import com.shudley.lottocombinationgenerator.utils.TicketChecker

@Composable
fun TicketCheckerScreen() {

    var ticket by remember { mutableStateOf("") }
    var winning by remember { mutableStateOf("") }
    var ticketBonus by remember { mutableStateOf("") }
    var winningBonus by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "🎫 Ticket Checker",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = ticket,
            onValueChange = { ticket = it },
            label = { Text("Your Numbers (1,2,3,4,5,6)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = winning,
            onValueChange = { winning = it },
            label = { Text("Winning Numbers") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = ticketBonus,
            onValueChange = { ticketBonus = it },
            label = { Text("Your Bonus Number") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = winningBonus,
            onValueChange = { winningBonus = it },
            label = { Text("Winning Bonus Number") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {

                try {

                    val ticketNumbers =
                        ticket.split(",")
                            .map { it.trim().toInt() }

                    val winningNumbers =
                        winning.split(",")
                            .map { it.trim().toInt() }

                    val check = TicketChecker.checkTicket(
                        ticket = ticketNumbers,
                        winning = winningNumbers,
                        ticketBonus = ticketBonus.toIntOrNull(),
                        winningBonus = winningBonus.toIntOrNull()
                    )

                    val prize = PrizeCalculator.getPrize(
                        check.matches,
                        check.bonusMatched
                    )

                    result =
                        "✅ Matches: ${check.matches}\n\n" +
                                "⭐ Bonus Match: ${check.bonusMatched}\n\n" +
                                "🏆 Prize: $prize"

                } catch (e: Exception) {

                    result = "❌ Please enter valid numbers.\nExample:\n1,5,12,24,36,44"

                }

            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Check Ticket")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = result,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}