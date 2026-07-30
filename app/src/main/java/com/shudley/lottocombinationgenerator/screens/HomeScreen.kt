package com.shudley.lottocombinationgenerator.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.shudley.lottocombinationgenerator.components.FeatureCard
import com.shudley.lottocombinationgenerator.components.GameSelector
import com.shudley.lottocombinationgenerator.models.Game

@Composable
fun HomeScreen(
    navController: NavController,
    selectedGame: Game,
    onGameSelected: (Game) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(
            text = "🎱 Lotto Master SA",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "South African Lottery Companion",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary
        )

        GameSelector(
            selectedGame = selectedGame,
            onGameSelected = onGameSelected
        )

        FeatureCard(
            icon = "🎲",
            title = "Random Generator",
            description = "Generate lucky lottery numbers instantly."
        ) {
            navController.navigate("random")
        }

        FeatureCard(
            icon = "✏️",
            title = "Own Numbers Creator",
            description = "Create and save your own lottery numbers."
        ) {
            navController.navigate("creator")
        }

        FeatureCard(
            icon = "🧮",
            title = "Combination Generator",
            description = "Generate multiple combinations from your numbers."
        ) {
            navController.navigate("combination")
        }

        FeatureCard(
            icon = "🎫",
            title = "Ticket Checker",
            description = "Check your ticket against winning numbers."
        ) {
            navController.navigate("ticket")
        }

        FeatureCard(
            icon = "📊",
            title = "Statistics",
            description = "View hot, cold and frequently drawn numbers."
        ) {
            navController.navigate("statistics")
        }

        FeatureCard(
            icon = "📅",
            title = "Results & Past Draws",
            description = "Browse the latest lottery results."
        ) {
            navController.navigate("results")
        }

        FeatureCard(
            icon = "💾",
            title = "Saved Tickets",
            description = "Manage your saved lottery tickets."
        ) {
            navController.navigate("saved")
        }

        FeatureCard(
            icon = "⚙️",
            title = "Settings",
            description = "Customize your Lotto Master SA experience."
        ) {
            navController.navigate("settings")
        }
    }
}