package com.shudley.lottocombinationgenerator.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shudley.lottocombinationgenerator.utils.TicketStorage

@Composable
fun SavedTicketsScreen() {

    var tickets by remember {
        mutableStateOf(TicketStorage.getAll())
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Saved Tickets",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (tickets.isEmpty()) {

            Text("No saved tickets yet.")

        } else {

            LazyColumn {

                items(tickets) { ticket ->

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp)
                    ) {

                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {

                            Text(
                                text = ticket,
                                style = MaterialTheme.typography.bodyLarge
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            Button(
                                onClick = {
                                    TicketStorage.delete(ticket)
                                    tickets = TicketStorage.getAll()
                                }
                            ) {
                                Text("Delete")
                            }

                        }

                    }

                }

            }

        }

    }

}