package com.shudley.lottocombinationgenerator.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.shudley.lottocombinationgenerator.models.Game
import com.shudley.lottocombinationgenerator.models.games

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameSelector(
    selectedGame: Game,
    onGameSelected: (Game) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {

        OutlinedTextField(
            value = selectedGame.name,
            onValueChange = {},
            readOnly = true,
            label = { Text("Select Game") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded)
            },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {

            games.forEach { game ->

                DropdownMenuItem(
                    text = { Text(game.name) },
                    onClick = {
                        onGameSelected(game)
                        expanded = false
                    }
                )
            }
        }
    }
}