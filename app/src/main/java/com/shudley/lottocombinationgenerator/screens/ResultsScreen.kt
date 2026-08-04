package com.shudley.lottocombinationgenerator.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shudley.lottocombinationgenerator.components.LotteryBall
import com.shudley.lottocombinationgenerator.models.Game
import com.shudley.lottocombinationgenerator.network.LotteryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray

@Composable
fun ResultsScreen(selectedGame: Game) {

    var results by remember {
        mutableStateOf<List<ResultItem>>(emptyList())
    }

    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Text(
            text = "Latest ${selectedGame.name} Results",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {

                scope.launch(Dispatchers.IO) {

                    try {

                        val json = LotteryRepository.getLatestResults()

                        val array = JSONArray(json)

                        val list = mutableListOf<ResultItem>()

                        for (i in 0 until array.length()) {

                            val obj = array.getJSONObject(i)

                            val numsJson = obj.getJSONArray("numbers")

                            val nums = mutableListOf<Int>()

                            for (j in 0 until numsJson.length()) {
                                nums.add(numsJson.getInt(j))
                            }

                            list.add(
                                ResultItem(
                                    game = obj.getString("game"),
                                    drawDate = obj.getString("drawDate"),
                                    numbers = nums,
                                    bonus = obj.getInt("bonus")
                                )
                            )
                        }

                        withContext(Dispatchers.Main) {
                            results = list
                        }

                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                }

            }
        ) {
            Text("Load Results")
        }

        Spacer(modifier = Modifier.height(20.dp))

        results.forEach { result ->

            Text("🎱 ${result.game}")

            Spacer(modifier = Modifier.height(6.dp))

            Text("📅 ${result.drawDate}")

            Spacer(modifier = Modifier.height(10.dp))

            Row {

                result.numbers.forEach {

                    LotteryBall(number = it)

                    Spacer(modifier = Modifier.width(6.dp))
                }

            }

            Spacer(modifier = Modifier.height(10.dp))

            Text("⭐ Bonus Ball")

            Spacer(modifier = Modifier.height(6.dp))

            LotteryBall(number = result.bonus)

            Spacer(modifier = Modifier.height(20.dp))
        }

    }
}

data class ResultItem(
    val game: String,
    val drawDate: String,
    val numbers: List<Int>,
    val bonus: Int
)