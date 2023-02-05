package com.example.muzz.compose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.muzz.R
import com.example.muzz.data.Message
import com.example.muzz.ui.theme.MuzzTheme

@Composable
fun ChatMessage(
    modifier: Modifier = Modifier,
    message: Message,
    hasTail: Boolean = false,
    showTimestamp: Boolean = false
) {
    val messageShape = if (!hasTail) {
        RoundedCornerShape(7.dp)
    } else if (message.isOutgoing) {
        RoundedCornerShape(7.dp, 7.dp, 0.dp, 7.dp)
    } else {
        RoundedCornerShape(0.dp, 7.dp, 7.dp, 7.dp)
    }

    val messageColor = if (message.isOutgoing) {
        MaterialTheme.colors.primary
    } else {
        MaterialTheme.colors.secondary
    }

    MuzzTheme {
        if (showTimestamp) {
            MuzzTimestamp(message.timestamp)
        }
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = if (message.isOutgoing) Alignment.End else Alignment.Start
        ) {
            Surface(
                modifier = modifier.wrapContentSize(),
                shape = messageShape,
                color = messageColor
            ) {
                Box {
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = message.message
                    )
                    if (message.isOutgoing) {
                        Icon(
                            modifier = Modifier
                                .size(10.dp)
                                .align(Alignment.BottomEnd),
                            painter = painterResource(id = R.drawable.baseline_keyboard_double_arrow_up_24),
                            contentDescription = "Message received"
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ChatMessageIncomingPreview() {
    ChatMessage(
        message = Message(
            message = "Incoming preview",
            isOutgoing = false
        ),
        hasTail = true
    )
}

@Preview
@Composable
fun ChatMessageOutgoingPreview() {
    ChatMessage(
        message = Message(
            message = "Outgoing preview",
            isOutgoing = true
        ),
        hasTail = true
    )
}
