package com.harsh.samples.geminiproapisample.domain.repository

import com.harsh.samples.geminiproapisample.domain.model.ChatMessage
import kotlinx.coroutines.flow.Flow

/*
* Sample shows implementation of only 1 chat, you can add multiple chats support
* */
interface ChatRepository {
    fun saveMessage(chatMessage: ChatMessage)
    fun getAllMessages(): List<ChatMessage>
    fun subscribeMessages(): Flow<List<ChatMessage>>
}
