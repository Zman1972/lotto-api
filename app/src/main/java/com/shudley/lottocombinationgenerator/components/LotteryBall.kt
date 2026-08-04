package com.shudley.lottocombinationgenerator.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun LotteryBall(
    number: Int,
    bonus: Boolean = false
) {

    val colors = if (bonus) {
        listOf(
            Color(0xFFFFF176),
            Color(0xFFFFC107)
        )
    } else {
        when (number % 6) {
            0 -> listOf(Color(0xFFFF8A80), Color(0xFFD32F2F))
            1 -> listOf(Color(0xFF90CAF9), Color(0xFF1976D2))
            2 -> listOf(Color(0xFFA5D6A7), Color(0xFF388E3C))
            3 -> listOf(Color(0xFFFFCC80), Color(0xFFF57C00))
            4 -> listOf(Color(0xFFCE93D8), Color(0xFF7B1FA2))
            else -> listOf(Color(0xFFFFFF8D), Color(0xFFFBC02D))
        }
    }

    Box(
        modifier = Modifier
            .size(48.dp)
            .shadow(8.dp, CircleShape)
            .clip(CircleShape)
            .background(
                Brush.radialGradient(colors)
            )
            .border(
                2.dp,
                Color.White,
                CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = number.toString(),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.ExtraBold,
            color = if (bonus) Color.Black else Color.White
        )
    }
}