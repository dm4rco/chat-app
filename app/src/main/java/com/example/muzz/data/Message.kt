package com.example.muzz.data

data class Message(
    val message: String,
    val timestamp: Long = System.currentTimeMillis(),
    val isOutgoing: Boolean = true
)
