package com.harsh.samples.geminiproapisample.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.harsh.samples.geminiproapisample.domain.model.ChatMessage
import com.harsh.samples.geminiproapisample.domain.model.Role
import java.util.Date


@Composable
fun ChatItem(chatMessage: ChatMessage) {
    Column {
        Text(text = if (chatMessage.role == Role.USER) "You" else "Gemini Pro", style = MaterialTheme.typography.labelSmall)
        Spacer(modifier = Modifier.size(2.dp))
        Text(text = chatMessage.message, style = MaterialTheme.typography.bodyMedium)
    }
}

@Preview
@Composable
fun PreviewChatItem() {
    ChatItem(chatMessage = ChatMessage(0, "Hello, this is a sample text to preview", Role.USER, Date()))
}
