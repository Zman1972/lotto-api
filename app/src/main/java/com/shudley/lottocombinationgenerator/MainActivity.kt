package com.shudley.lottocombinationgenerator

import com.shudley.lottocombinationgenerator.utils.TicketStorage
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shudley.lottocombinationgenerator.models.games
import com.shudley.lottocombinationgenerator.screens.*
import com.shudley.lottocombinationgenerator.screens.OwnNumbersScreen
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TicketStorage.init(applicationContext)

        setContent {

            val navController = rememberNavController()

            var selectedGame by remember {
                mutableStateOf(games.first())
            }

            NavHost(
                navController = navController,
                startDestination = "home"
            ) {

                composable("home") {
                    HomeScreen(
                        navController = navController,
                        selectedGame = selectedGame,
                        onGameSelected = {
                            selectedGame = it
                        }
                    )
                }

                composable("random") {
                    LottoApp(selectedGame)
                }

                composable("creator") {
                    OwnNumbersScreen(selectedGame)
                }

                composable("combination") {
                    CombinationScreen(selectedGame)
                }

                composable("ticket") {
                    TicketCheckerScreen()
                }

                composable("statistics") {
                    StatisticsScreen()
                }

                composable("results") {
                    ResultsScreen()
                }

                composable("saved") {
                    SavedTicketsScreen()
                }

                composable("settings") {
                    SettingsScreen()
                }
            }
        }
    }
}