package com.shudley.lottocombinationgenerator.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shudley.lottocombinationgenerator.network.LotteryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject

@Composable
fun ResultsScreen() {

    var results by remember {
        mutableStateOf("Press Load Results")
    }

    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Latest Lottery Results",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {

                results = "Loading..."

                scope.launch(Dispatchers.IO) {

                    val json = LotteryRepository.getLatestResults()

                    val formatted = try {

                        val obj = JSONObject(json)

                        val game = obj.getString("game")
                        val drawDate = obj.getString("drawDate")
                        val numbers = obj.getJSONArray("numbers")
                        val bonus = obj.getInt("bonus")

                        val nums = buildString {
                            for (i in 0 until numbers.length()) {
                                append(numbers.getInt(i))
                                if (i < numbers.length() - 1) append(" • ")
                            }
                        }

                        """
🎱 $game

📅 Draw Date
$drawDate

🔢 Winning Numbers
$nums

⭐ Bonus Ball
$bonus
                        """.trimIndent()

                    } catch (e: Exception) {
                        json
                    }

                    withContext(Dispatchers.Main) {
                        results = formatted
                    }
                }
            }
        ) {
            Text("Load Results")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = results,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}