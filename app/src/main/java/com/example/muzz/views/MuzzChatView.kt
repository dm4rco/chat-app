package com.example.muzz.views

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.muzz.R
import com.example.muzz.compose.ChatMessage
import com.example.muzz.ui.theme.MuzzTheme
import com.example.muzz.viewmodels.MuzzChatViewModel

@Composable
fun Chat() {
    /* The view of the chat. It consists of a Constraint-layout which has
    * - The MuzzTopBar
    * - The MuzzChatContent
    * - The MuzzTextInput
    */
    val viewModel = viewModel<MuzzChatViewModel>()

    ConstraintLayout(Modifier.fillMaxSize()) {
        val (topBarRef, chatMessagesRef, inputRef) = createRefs()

        MuzzTopAppBar(
            Modifier.constrainAs(topBarRef) {
                top.linkTo(parent.top)
            }
        )
        MuzzChatContent(
            modifier = Modifier.constrainAs(chatMessagesRef) {
                top.linkTo(topBarRef.bottom)
                bottom.linkTo(inputRef.top)
                height = Dimension.fillToConstraints
            },
            viewModel = viewModel
        )
        MuzzTextInput(
            modifier = Modifier.constrainAs(inputRef) {
                top.linkTo(chatMessagesRef.bottom)
                bottom.linkTo(parent.bottom)
            },
            viewModel = viewModel
        )
    }
}

@Composable
private fun MuzzTopAppBar(modifier: Modifier = Modifier) {
    TopAppBar(
        modifier = modifier.fillMaxWidth(),
        backgroundColor = MaterialTheme.colors.background,
        elevation = 10.dp
    ) {
        Row(
            modifier = Modifier.padding(vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                modifier = Modifier.padding(8.dp),
                painter = painterResource(id = R.drawable.ic_arrow_back_black_24),
                contentDescription = "Back Button"
            )
            Image(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(36.dp)
                    .background(MaterialTheme.colors.primary),
                painter = painterResource(id = R.drawable.ic_android_black_24dp),
                contentDescription = "Sarah's Profile Picture"
            )
            Text(
                modifier = Modifier.weight(1f),
                text = "Sarah"
            )
            Icon(
                modifier = Modifier
                    .weight(0.1f)
                    .padding(end = 8.dp),
                painter = painterResource(id = R.drawable.baseline_more_horiz_48),
                contentDescription = "Settings"
            )
        }
    }
}

@SuppressLint("MutableCollectionMutableState")
@Composable
private fun MuzzChatContent(
    modifier: Modifier = Modifier,
    viewModel: MuzzChatViewModel
) {
    val messages = viewModel.messages

    val listState = rememberLazyListState()

    LaunchedEffect(messages.size) {
        listState.animateScrollToItem(messages.size)
    }

    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        state = listState
    ) {
        items(messages.size) { index ->
            ChatMessage(
                message = messages[index],
                hasTail = viewModel.hasTail(index),
                showTimestamp = viewModel.showTimestamp(index)
            )
            Spacer(modifier = Modifier.padding(10.dp))
        }
    }
}

@Composable
private fun MuzzTextInput(
    modifier: Modifier = Modifier,
    viewModel: MuzzChatViewModel
) {
    val chatMessage = remember { mutableStateOf("") }
    val interactionSource = remember { MutableInteractionSource() }

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background),
        elevation = 50.dp
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                modifier = Modifier.weight(0.9f)
                    .border(
                        width = 2.dp,
                        color = MaterialTheme.colors.primary,
                        shape = CircleShape
                    ),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = MaterialTheme.colors.background,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                shape = AbsoluteRoundedCornerShape(1000.dp),
                value = chatMessage.value,
                onValueChange = { chatMessage.value = it }
            )
            Icon(
                modifier = Modifier
                    .size(48.dp)
                    .clickable(
                        indication = null,
                        interactionSource = interactionSource
                    ) {
                        if (chatMessage.value.isNotBlank()) {
                            viewModel.onSendClicked(chatMessage.value.trim())
                            chatMessage.value = ""
                        }
                    }
                    .padding(10.dp)
                    .align(Alignment.CenterVertically),
                painter = painterResource(id = R.drawable.baseline_arrow_forward_ios_24),
                contentDescription = "Send message",
                tint = MaterialTheme.colors.primary
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MuzzTheme {
        Chat()
    }
}
