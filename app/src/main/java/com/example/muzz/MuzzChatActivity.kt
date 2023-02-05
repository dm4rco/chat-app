package com.example.muzz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.muzz.ui.theme.MuzzTheme
import com.example.muzz.views.Chat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Standard activity that launches the Compose "Chat()"
        setContent {
            MuzzTheme {
                Chat()
            }
        }
    }
}
