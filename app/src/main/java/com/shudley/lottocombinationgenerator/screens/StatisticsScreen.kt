package com.shudley.lottocombinationgenerator.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shudley.lottocombinationgenerator.models.WinningResult
import com.shudley.lottocombinationgenerator.network.LotteryRepository
import com.shudley.lottocombinationgenerator.utils.StatisticsCalculator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray

@Composable
fun StatisticsScreen() {

    var stats by remember {
        mutableStateOf<com.shudley.lottocombinationgenerator.models.Statistics?>(null)
    }

    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {

        scope.launch(Dispatchers.IO) {

            try {

                val json = LotteryRepository.getLatestResults()

                val array = JSONArray(json)

                val results = mutableListOf<WinningResult>()

                for (i in 0 until array.length()) {

                    val obj = array.getJSONObject(i)

                    val numbersJson = obj.getJSONArray("numbers")

                    val numbers = mutableListOf<Int>()

                    for (j in 0 until numbersJson.length()) {
                        numbers.add(numbersJson.getInt(j))
                    }

                    results.add(
                        WinningResult(
                            game = obj.getString("game"),
                            drawDate = obj.getString("drawDate"),
                            numbers = numbers,
                            bonus = obj.getInt("bonus")
                        )
                    )
                }

                val calculated = StatisticsCalculator.calculate(results)

                withContext(Dispatchers.Main) {
                    stats = calculated
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "📊 Lottery Statistics",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        if (stats == null) {

            Text("Loading statistics...")

        } else {

            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("🔥 Hot Numbers")
                    Text(stats!!.hotNumbers.joinToString(", "))
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("❄️ Cold Numbers")
                    Text(stats!!.coldNumbers.joinToString(", "))
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("📅 Total Draws")
                    Text(stats!!.totalDraws.toString())
                }
            }
        }
    }
}