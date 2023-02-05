package com.example.muzz.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun MuzzTimestamp(
    timestamp: Long
) {
    val formatter = SimpleDateFormat("E HH:mm", Locale.UK)
    val current = formatter.format(timestamp)
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        text = current,
        textAlign = TextAlign.Center
    )
}
