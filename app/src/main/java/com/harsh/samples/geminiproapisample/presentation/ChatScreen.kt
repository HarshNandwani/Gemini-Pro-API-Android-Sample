package com.harsh.samples.geminiproapisample.presentation

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.harsh.samples.geminiproapisample.domain.model.ChatMessage
import com.harsh.samples.geminiproapisample.domain.model.Role
import com.harsh.samples.geminiproapisample.presentation.theme.RoyalBlue
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    uiState: UiState,
    onPromptTextChange: (String) -> Unit,
    sendMessage: () -> Unit
) {

    val focusManager = LocalFocusManager.current

    val value by rememberInfiniteTransition().animateFloat(
        initialValue = 0.5f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 600,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Gemini Pro API Sample") },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = RoyalBlue,
                    titleContentColor = Color.White
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(vertical = 24.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {

            LazyColumn(
                contentPadding = PaddingValues(24.dp),
                modifier = Modifier.weight(0.8f),
                verticalArrangement = Arrangement.Bottom
            ) {
                uiState.allMessages.forEach {
                    item {
                        Spacer(modifier = Modifier.size(16.dp))
                        ChatItem(chatMessage = it)
                    }
                }
            }

            if (uiState.responseState == ResponseState.GENERATING) {
                Box(modifier = Modifier
                    .padding(start = 16.dp)
                    .graphicsLayer {
                        scaleX = value
                        scaleY = value
                    }
                    .size(25.dp)
                    .clip(CircleShape)
                    .background(Color.Red)

                )
            }

            TextField(
                placeholder = { Text(text = "Enter your prompt") },
                value = uiState.currentPrompt,
                onValueChange = {
                    onPromptTextChange(it)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
                    .border(
                        BorderStroke(width = 2.dp, color = MaterialTheme.colorScheme.primary),
                        shape = RoundedCornerShape(50)
                    ),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(50),
                trailingIcon = {
                    IconButton(onClick = {
                        sendMessage()
                        focusManager.clearFocus()
                    }) {
                        Icon(imageVector = Icons.Default.Send, contentDescription = "Send")
                    }
                }
            )

        }
    }
}

@Preview
@Composable
fun PreviewChatScreen() {
    val uiState = UiState().copy(
        allMessages = listOf(
            ChatMessage(1, "Hi, this is a sample user message", Role.USER, Date()),
            ChatMessage(
                2,
                "Hi, this is a sample response from the model, Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed dictum, libero a molestie cursus, tellus mauris feugiat risus, ac varius arcu odio fringilla dui. Fusce ac tristique diam. Morbi eleifend ex porttitor faucibus varius. Vivamus in pharetra urna. Donec erat purus, consectetur ut dapibus ac, suscipit sed felis. In eget felis bibendum, tincidunt neque maximus, ultricies ante. Curabitur quis malesuada leo. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Sed et auctor neque. Vestibulum quis mollis felis, quis lobortis ante.",
                Role.MODEL,
                Date()
            )
        ),
        currentPrompt = "Enter your prompt",
        responseState = ResponseState.GENERATING
    )

    ChatScreen(
        uiState,
        onPromptTextChange = {  },
        sendMessage = { }
    )
}