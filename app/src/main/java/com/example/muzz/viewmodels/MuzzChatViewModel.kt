package com.example.muzz.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.muzz.data.Message
import com.example.muzz.data.mockResponses
import kotlin.random.Random

class MuzzChatViewModel : ViewModel() {

    // This is being observed by the UI to show always the latest messages
    // Room database is missing. I would probably use a Room database inside of the
    // repository
    private val _messages = mutableStateListOf<Message>()
    val messages = _messages

    // function that is called when user clicks on the send icon
    fun onSendClicked(message: String) {
        _messages.add(Message(message))

        // The rest of the code is for mock responses for testing purposes
        val randomBoolean = Random.nextBoolean()
        val responseIndex = Random.nextInt(until = mockResponses.size)

        if (randomBoolean) {
            _messages.add(
                Message(
                    message = mockResponses[responseIndex],
                    isOutgoing = false
                )
            )
        }
    }

    // helper function to check if a timestamp should be added
    fun showTimestamp(messageIndex: Int): Boolean {
        return messageIndex == 0 ||
            (_messages[messageIndex].timestamp + SHOW_TIMESTAMP_DURATION) < System.currentTimeMillis()
    }

    // helper function that should check if a tail is needed
    fun hasTail(messageIndex: Int): Boolean {
        return true //TODO I wasn't able to implement this functionality in the timeframe
    }
}

const val SHOW_TIMESTAMP_DURATION = (60 * 60 * 1000) // one hour in ms
