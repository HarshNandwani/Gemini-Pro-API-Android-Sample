package com.harsh.samples.geminiproapisample.domain.repository

import com.harsh.samples.geminiproapisample.domain.model.ChatMessage
import kotlinx.coroutines.flow.Flow

/*
* Currently only supports one chat
*
* TODO: Add support for multiple chats?
* */
interface ChatRepository {
    fun saveMessage(chatMessage: ChatMessage)
    fun getAllMessages(): List<ChatMessage>
    fun subscribeMessages(): Flow<List<ChatMessage>>
}
